package com.shaw.iam.core.auth.handler;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.shaw.auth.authentication.GetAuthLoginTypeService;
import com.shaw.auth.entity.AuthLoginType;
import com.shaw.auth.exception.ClientNotFoundException;
import com.shaw.iam.core.client.dao.LoginTypeDao;
import com.shaw.iam.core.client.entity.LoginType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取认证终端
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetAuthLoginTypeServiceImpl implements GetAuthLoginTypeService {

	private final LoginTypeDao loginTypeDao;

	/**
	 * 获取认证终端信息
	 */
	@Override
	public AuthLoginType getAuthLoginType(String loginType) {
		LoginType type = loginTypeDao.findByCode(loginType).orElseThrow(ClientNotFoundException::new);
		AuthLoginType authLoginType = new AuthLoginType();
		BeanUtils.copyProperties(type, authLoginType);
		return authLoginType;
	}

}
