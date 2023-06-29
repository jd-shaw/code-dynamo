package com.shaw.iam.core.permission.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.param.permission.PermPathBatchEnableParam;
import com.shaw.iam.param.permission.PermPathParam;

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

	@Override
	public void save(PermPathParam param) {
		PermPath permPath = PermPath.init(param);
		getPermPathDao().save(permPath);
	}

	@Override
	@CacheEvict(value = { CachingCode.USER_PATH, CachingCode.IGNORE_PATH }, allEntries = true)
	public void update(PermPathParam param) {
		PermPath permPath = getPermPathDao().findById(param.getId()).orElseThrow(() -> new BaseException("信息不存在"));
		BeanUtils.copyProperties(param, permPath);
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

}
