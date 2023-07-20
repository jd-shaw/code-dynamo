package com.shaw.sys.core.service.impl;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.mysql.jpa.po.PageQuery;
import com.shaw.sys.core.code.CachingCode;
import com.shaw.sys.core.dao.SystemParameterDao;
import com.shaw.sys.core.dto.SystemParameterDto;
import com.shaw.sys.core.entity.SystemParameter;
import com.shaw.sys.core.param.SystemParameterParam;
import com.shaw.sys.core.service.SystemParameterService;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.bean.BeanUtilsBean;

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
	@Transactional
	public void add(SystemParameterParam param) {
		SystemParameter systemParameter = SystemParameter.init(param);
		if (getSystemParameterDao().existsByParamKey(systemParameter.getParamKey())) {
			throw new BaseException("key重复");
		}
		// 默认非内置
		systemParameter.setInternal(false);
		systemParameter.setId(RandomUIDUtils.getUID());
		systemParameter.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
		getSystemParameterDao().save(systemParameter);
	}

	/**
	 * 更新
	 */
	@Override
	@Transactional
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
	public PageResult<SystemParameterDto> page(PageQuery query) {
		Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());
		Page<SystemParameter> page = getSystemParameterDao().findAll(pageable, query);
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
	@Cacheable(value = CachingCode.SYSTEM_PARAM, key = "#key")
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
