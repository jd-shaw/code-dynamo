package com.shaw.iam.core.permission.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.core.permission.dao.PermPathDao;
import com.shaw.iam.core.permission.entity.PermPath;
import com.shaw.iam.core.permission.service.PermPathService;
import com.shaw.iam.core.upms.service.RolePathService;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.core.user.service.UserInfoService;
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.dto.upms.RolePathDto;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.param.permission.PermPathBatchEnableParam;
import com.shaw.iam.param.permission.PermPathParam;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.bean.BeanUtilsBean;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shaw
 * @date 2023/6/28
 */
@Getter
@Service
@AllArgsConstructor
public class PermPathServiceImpl implements PermPathService {

	private final PermPathDao permPathDao;

	private final RolePathService rolePathService;
	private final UserInfoService userInfoService;
	private final UserRoleService userRoleService;

	@Override
	public void save(PermPathParam param) {
		PermPath permPath = PermPath.init(param);
		permPath.setId(RandomUIDUtils.getUID());
		permPath.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
		getPermPathDao().save(permPath);
	}

	@Override
	@CacheEvict(value = { CachingCode.USER_PATH, CachingCode.IGNORE_PATH }, allEntries = true)
	public void update(PermPathParam param) {
		PermPath permPath = getPermPathDao().findById(param.getId()).orElseThrow(() -> new BaseException("信息不存在"));
		BeanUtils.copyProperties(param, permPath, BeanUtilsBean.getNullPropertyNames(param));
		permPath.setGenerate(false);// 编辑过的信息不再作为系统生成的
		getPermPathDao().save(permPath);
	}

	@Override
	public void batchUpdateEnable(PermPathBatchEnableParam param) {
		List<PermPath> permPaths = getPermPathDao().findAllById(param.getPermPathIds());
		permPaths.forEach(permPath -> permPath.setEnable(param.isEnable()).setGenerate(false));
		getPermPathDao().saveAll(permPaths);
	}

	/**
	 * 删除
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_PATH, CachingCode.IGNORE_PATH }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		getRolePathService().deleteByPermissionId(id);
		getPermPathDao().deleteById(id);
	}

	/**
	 * 删除
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_PATH, CachingCode.IGNORE_PATH }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void delete(List<String> ids) {
		ids.forEach(id -> getRolePathService().deleteByPermissionId(id));
		getPermPathDao().deleteAllById(ids);
	}

	/**
	 * 获取单条
	 */
	@Override
	public PermPathDto findById(String id) {
		return getPermPathDao().findById(id).map(PermPath::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 根据ids查询权限信息
	 */
	@Override
	public List<PermPathDto> findByIds(List<String> ids) {
		return ResultConvertUtil.dtoListConvert(getPermPathDao().findAllById(ids));
	}

	/**
	 * 查询用户查询拥有的请求权限信息
	 */
	@Override
	public List<PermPathDto> findPathsByUser(String userId) {
		UserInfoDto userInfo = getUserInfoService().findById(userId);

		List<PermPathDto> paths;
		if (userInfo.isAdmin()) {
			paths = findAll();
		} else {
			paths = this.findPermissionsByUser(userId);
		}
		return paths;

	}

	/**
	 * 列表
	 */
	@Override
	public List<PermPathDto> findAll() {
		return ResultConvertUtil.dtoListConvert(getPermPathDao().findAll());
	}

	@Override
	public PageResult<PermPathDto> page(PageParam pageParam, PermPathParam param) {
		Specification<PermPath> specification = new Specification<PermPath>() {
			@Override
			public Predicate toPredicate(Root<PermPath> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.hasLength(param.getCode())) {
					predicateList
							.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + param.getCode() + "%"));
				}
				if (StringUtils.hasLength(param.getName())) {
					predicateList
							.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + param.getName() + "%"));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
		Page<PermPath> page = getPermPathDao().findAll(specification, pageable);
		return new PageResult<PermPathDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	/**
	 * 查询用户查询拥有的权限信息
	 */
	private List<PermPathDto> findPermissionsByUser(String userId) {
		List<PermPathDto> permissions = new ArrayList<>(0);

		List<String> roleIds = getUserRoleService().findRoleIdsByUserId(userId);
		if (CollectionUtils.isEmpty(roleIds)) {
			return permissions;
		}
		List<RolePathDto> rolePaths = getRolePathService().findIdsByRole(roleIds);
		List<String> permissionIds = rolePaths.stream().map(RolePathDto::getPermissionId).distinct()
				.collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(permissionIds)) {
			permissions = findByIds(permissionIds);
		}
		return permissions;
	}
}
