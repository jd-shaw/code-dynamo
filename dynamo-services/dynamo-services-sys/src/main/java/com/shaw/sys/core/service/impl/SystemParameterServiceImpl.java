package com.shaw.sys.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shaw.utils.bean.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
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
import com.shaw.sys.core.dao.SystemParameterDao;
import com.shaw.sys.core.dto.SystemParameterDto;
import com.shaw.sys.core.entity.SystemParameter;
import com.shaw.sys.core.param.DictionaryParam;
import com.shaw.sys.core.param.SystemParameterParam;
import com.shaw.sys.core.service.SystemParameterService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统参数
 *
 * @author shaw
 * @date 2021/10/25
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class SystemParameterServiceImpl implements SystemParameterService {

	private final SystemParameterDao systemParameterDao;

	/**
	 * 添加
	 */
	@Override
	public void add(SystemParameterParam param) {
		SystemParameter systemParameter = SystemParameter.init(param);
		if (getSystemParameterDao().existsByParamKey(systemParameter.getParamKey())) {
			throw new BaseException("key重复");
		}
		// 默认非内置
		systemParameter.setInternal(false);
		getSystemParameterDao().save(systemParameter);
	}

	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SystemParameterParam param) {
		SystemParameter systemParameter = getSystemParameterDao().findById(param.getId())
				.orElseThrow(() -> new BaseException("参数项不存在"));

		if (getSystemParameterDao().existsByParamKeyAndIdNot(param.getParamKey(), param.getId())) {
			throw new BaseException("key重复");
		}
		BeanUtils.copyProperties(param, systemParameter, BeanUtilsBean.getNullPropertyNames(param));
		getSystemParameterDao().save(systemParameter);
	}

	/**
	 * 分页
	 */
	@Override
	public PageResult<SystemParameterDto> page(PageParam pageParam, SystemParameterParam clientParam) {
		Specification<SystemParameter> specification = new Specification<SystemParameter>() {
			@Override
			public Predicate toPredicate(Root<SystemParameter> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.hasLength(clientParam.getParamKey())) {
					predicateList.add(criteriaBuilder.like(root.get("paramKey").as(String.class),
							"%" + clientParam.getParamKey() + "%"));
				}
				if (StringUtils.hasLength(clientParam.getName())) {
					predicateList.add(
							criteriaBuilder.like(root.get("name").as(String.class), "%" + clientParam.getName() + "%"));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
		Page<SystemParameter> page = getSystemParameterDao().findAll(specification, pageable);
		return new PageResult<SystemParameterDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	/**
	 * 获取单条
	 */
	@Override
	public SystemParameterDto findById(String id) {
		return getSystemParameterDao().findById(id).map(SystemParameter::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 根据键名获取键值
	 */
	@Override
	public String findByParamKey(String key) {
		val param = getSystemParameterDao().findByParamKey(key).orElseThrow(DataNotExistException::new);
		if (Objects.equals(param.getEnable(), false)) {
			throw new BaseException("该参数已停用");
		}
		return param.getValue();
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(String id) {
		SystemParameter systemParameter = getSystemParameterDao().findById(id)
				.orElseThrow(() -> new BaseException("系统参数不存在"));
		if (systemParameter.isInternal()) {
			throw new BaseException("内置参数不可以被删除");
		}
		getSystemParameterDao().deleteById(id);
	}

	/**
	 * 判断编码是否存在
	 */
	@Override
	public boolean existsByKey(String key) {
		return getSystemParameterDao().existsByParamKey(key);
	}

	/**
	 * 判断编码是否存在
	 */
	@Override
	public boolean existsByKey(String key, String id) {
		return getSystemParameterDao().existsByParamKeyAndIdNot(key, id);
	}

	/**
	 * 获取参数值
	 */
	@Override
	public String getValue(String key) {
		return getSystemParameterDao().findByParamKey(key).map(SystemParameter::getValue).orElse(null);
	}

}
