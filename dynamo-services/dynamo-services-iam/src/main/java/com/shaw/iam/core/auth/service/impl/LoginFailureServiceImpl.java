package com.shaw.iam.core.auth.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.shaw.auth.exception.LoginFailureException;
import com.shaw.auth.service.LoginFailureService;

import lombok.RequiredArgsConstructor;

/**
 * 登录失败
 *
 * @author shaw
 * @date 2023/06/20
 */
@Service
@RequiredArgsConstructor
public class LoginFailureServiceImpl implements LoginFailureService {

	@Override
	public void onLoginFailure(HttpServletRequest request, HttpServletResponse response, LoginFailureException e) {
		//TODO  记录登录日志
	}

}
