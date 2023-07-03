package com.shaw.iam.core.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.utils.RandomUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.core.client.dao.LoginTypeDao;
import com.shaw.iam.core.client.entity.LoginType;
import com.shaw.iam.core.client.service.LoginTypeService;
import com.shaw.iam.dto.client.LoginTypeDto;
import com.shaw.iam.param.client.LoginTypeParam;
import com.shaw.utils.bean.BeanUtilsBean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/6/30
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginTypeServiceImpl implements LoginTypeService {

	private final LoginTypeDao loginTypeDao;

	@Override
	public PageResult<LoginTypeDto> page(PageParam pageParam, LoginTypeParam param) {
		Specification<LoginType> specification = new Specification<LoginType>() {
			@Override
			public Predicate toPredicate(Root<LoginType> root, CriteriaQuery<?> criteriaQuery,
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
		Page<LoginType> page = getLoginTypeDao().findAll(specification, pageable);
		return new PageResult<LoginTypeDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	/**
	 * 添加
	 */
	@Override
	public LoginTypeDto add(LoginTypeParam param) {
		if (getLoginTypeDao().existsByCode(param.getCode())) {
			throw new BaseException("终端编码不得重复");
		}
		LoginType loginType = LoginType.init(param);
		loginType.setIsSystem(0);
		loginType.setId(RandomUIDUtils.getUID());
		loginType.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
		return getLoginTypeDao().save(loginType).toDto();
	}

	/**
	 * 修改
	 */
	@Override
	public LoginTypeDto update(LoginTypeParam param) {
		LoginType loginType = getLoginTypeDao().findById(param.getId()).orElseThrow(() -> new BaseException("终端不存在"));
		if (getLoginTypeDao().existsByCodeAndIdNot(param.getCode(), loginType.getId())) {
			throw new BaseException("终端编码不得重复");
		}
		if (loginType.getIsSystem() == 1) {
			loginType.setEnable(loginType.getIsSystem());
		}
		BeanUtils.copyProperties(param, loginType, BeanUtilsBean.getNullPropertyNames(param));
		return getLoginTypeDao().save(loginType).toDto();
	}

	/**
	 * 获取单条
	 */
	@Override
	public LoginTypeDto findById(String id) {
		return getLoginTypeDao().findById(id).map(LoginType::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 获取单条
	 */
	@Override
	public LoginTypeDto findByCode(String code) {
		return getLoginTypeDao().findByCode(code).map(LoginType::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<LoginTypeDto> findAll() {
		return ResultConvertUtil.dtoListConvert(getLoginTypeDao().findAll());
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(String id) {
		LoginType loginType = getLoginTypeDao().findById(id).orElseThrow(DataNotExistException::new);
		if (loginType.getIsSystem() == 1) {
			throw new BaseException("系统内置终端，不可删除");
		}
		getLoginTypeDao().deleteById(id);
	}

	/**
	 * 编码是否已经存在
	 */
	@Override
	public boolean existsByCode(String code) {
		return getLoginTypeDao().existsByCode(code);
	}

	/**
	 * 编码是否已经存在(不包含自身)
	 */
	@Override
	public boolean existsByCode(String code, String id) {
		return getLoginTypeDao().existsByCodeAndIdNot(code, id);
	}

}
