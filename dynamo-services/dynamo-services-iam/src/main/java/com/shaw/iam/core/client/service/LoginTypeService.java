package com.shaw.iam.core.client.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.core.client.dao.LoginTypeDao;
import com.shaw.iam.core.client.entity.LonginType;
import com.shaw.iam.dto.client.LoginTypeDto;
import com.shaw.iam.param.client.LoginTypeParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 终端
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginTypeService {

	private final LoginTypeDao loginTypeDao;

	/**
	 * 添加
	 */
	public LoginTypeDto add(LoginTypeParam param) {
		if (loginTypeDao.existsByCode(param.getCode())) {
			throw new BaseException("终端编码不得重复");
		}
		LonginType longinType = LonginType.init(param);
		longinType.setIsSystem(0);
		return loginTypeDao.save(longinType).toDto();
	}

	/**
	 * 修改
	 */
	public LoginTypeDto update(LoginTypeParam param) {
		LonginType longinType = loginTypeDao.findById(param.getId()).orElseThrow(() -> new BaseException("终端不存在"));
		if (loginTypeDao.existsByCodeAndId(param.getCode(), longinType.getId())) {
			throw new BaseException("终端编码不得重复");
		}
		if (longinType.getIsSystem() == 1) {
			longinType.setEnable(longinType.getIsSystem());
		}
		BeanUtils.copyProperties(param, longinType);
		return loginTypeDao.save(longinType).toDto();
	}

	/**
	 * 分页
	 */
	//	public PageResult<LoginTypeDto> page(PageParam pageParam, LoginTypeParam loginTypeParam) {
	//		return MpUtil.convert2DtoPageResult(loginTypeManager.page(pageParam, loginTypeParam));
	//	}

	/**
	 * 超级查询
	 */
	//	public PageResult<LoginTypeDto> superPage(PageParam pageParam, QueryParams queryParams) {
	//		return MpUtil.convert2DtoPageResult(loginTypeManager.supperPage(pageParam, queryParams));
	//	}

	/**
	 * 获取单条
	 */
	public LoginTypeDto findById(String id) {
		return loginTypeDao.findById(id).map(LonginType::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 获取单条
	 */
	public LoginTypeDto findByCode(String code) {
		return loginTypeDao.findByCode(code).map(LonginType::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 获取全部
	 */
	public List<LoginTypeDto> findAll() {
		return ResultConvertUtil.dtoListConvert(loginTypeDao.findAll());
	}

	/**
	 * 删除
	 */
	public void delete(String id) {
		LonginType longinType = loginTypeDao.findById(id).orElseThrow(DataNotExistException::new);
		if (longinType.getIsSystem() == 1) {
			throw new BaseException("系统内置终端，不可删除");
		}
		loginTypeDao.deleteById(id);
	}

	/**
	 * 编码是否已经存在
	 */
	public boolean existsByCode(String code) {
		return loginTypeDao.existsByCode(code);
	}

	/**
	 * 编码是否已经存在(不包含自身)
	 */
	public boolean existsByCode(String code, String id) {
		return loginTypeDao.existsByCodeAndId(code, id);
	}

}
