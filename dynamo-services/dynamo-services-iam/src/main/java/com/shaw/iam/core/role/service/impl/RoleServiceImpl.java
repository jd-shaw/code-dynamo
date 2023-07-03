package com.shaw.iam.core.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.dto.KeyValue;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.core.role.dao.RoleDao;
import com.shaw.iam.core.role.entity.Role;
import com.shaw.iam.core.role.service.RoleService;
import com.shaw.iam.core.upms.service.RoleMenuService;
import com.shaw.iam.core.upms.service.RolePathService;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.exception.role.RoleAlreadyExistedException;
import com.shaw.iam.exception.role.RoleAlreadyUsedException;
import com.shaw.iam.exception.role.RoleNotExistedException;
import com.shaw.iam.param.role.RoleParam;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.bean.BeanUtilsBean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/6/28
 */

@Service
@Getter
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;
	private final UserRoleService userRoleService;
	private final RolePathService rolePathService;
	private final RoleMenuService roleMenuService;

	/**
	 * 添加
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public RoleDto add(RoleParam roleParam) {
		// Name唯一性校验（名称code不能相同）
		if (getRoleDao().existsByCode(roleParam.getCode()) || getRoleDao().existsByName(roleParam.getName())) {
			throw new RoleAlreadyExistedException();
		}
		Role role = Role.init(roleParam);
		role.setId(RandomUIDUtils.getUID());
		role.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
		return getRoleDao().save(role).toDto();
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public RoleDto update(RoleParam roleParam) {
		// name和code唯一性校验
		if (getRoleDao().existsByCodeAndIdNot(roleParam.getCode(), roleParam.getId())) {
			throw new RoleAlreadyExistedException();
		}
		if (getRoleDao().existsByNameAndIdNot(roleParam.getName(), roleParam.getId())) {
			throw new RoleAlreadyExistedException();
		}

		Role role = getRoleDao().findById(roleParam.getId()).orElseThrow(RoleNotExistedException::new);
		BeanUtils.copyProperties(roleParam, role, BeanUtilsBean.getNullPropertyNames(roleParam));
		return getRoleDao().save(role).toDto();
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = { CachingCode.USER_PATH }, allEntries = true)
	public void delete(String roleId) {
		if (Objects.isNull(roleId) || !getRoleDao().existsById(roleId)) {
			throw new RoleNotExistedException();
		}
		// 存在当前角色用户的场合不允许删除
		if (getUserRoleService().existsByRoleId(roleId)) {
			throw new RoleAlreadyUsedException();
		}
		// 删除角色信息
		getRoleDao().deleteById(roleId);
		// 删除关联的请求和菜单权限
		getRolePathService().deleteByRoleId(roleId);
		getRoleMenuService().deleteByRoleId(roleId);
	}

	/**
	 * 角色列表
	 */
	@Override
	public List<RoleDto> findAll() {
		return ResultConvertUtil.dtoListConvert(getRoleDao().findAll());
	}

	/**
	 * 角色分页
	 */
	@Override
	public PageResult<RoleDto> page(PageParam pageParam, RoleParam roleParam) {
		Specification<Role> specification = new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.hasLength(roleParam.getCode())) {
					predicateList.add(
							criteriaBuilder.like(root.get("code").as(String.class), "%" + roleParam.getCode() + "%"));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
		Page<Role> page = getRoleDao().findAll(specification, pageable);
		return new PageResult<RoleDto>().setSize(page.getSize()).setCurrent(page.getNumber() + 1)
				.setTotal(page.getTotalElements()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	/**
	 * 角色下拉框
	 */
	@Override
	public List<KeyValue> dropdown() {
		return getRoleDao().findAll().stream().map(role -> new KeyValue(String.valueOf(role.getId()), role.getName()))
				.collect(Collectors.toList());
	}

	/**
	 * 详情
	 */
	@Override
	public RoleDto findById(String id) {
		return ResultConvertUtil.dtoConvert(getRoleDao().findById(id));
	}

	@Override
	public List<RoleDto> findByIds(List<String> ids) {
		return ResultConvertUtil.dtoListConvert(getRoleDao().findAllById(ids));
	}

	/**
	 * code是否存在
	 */
	@Override
	public boolean existsByCode(String code) {
		return getRoleDao().existsByCode(code);
	}

	/**
	 * code是否存在
	 */
	@Override
	public boolean existsByCodeNotId(String code, String id) {
		return getRoleDao().existsByCodeAndIdNot(code, id);
	}

	/**
	 * name是否存在
	 */
	@Override
	public boolean existsByName(String name) {
		return getRoleDao().existsByName(name);
	}

	/**
	 * name是否存在
	 */
	@Override
	public boolean existsByNameNotId(String name, String id) {
		return getRoleDao().existsByNameAndIdNot(name, id);
	}

}
