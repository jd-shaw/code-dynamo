package com.shaw.iam.core.auth.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.shaw.auth.entity.AuthInfoResult;
import com.shaw.auth.service.LoginSuccessService;

import lombok.RequiredArgsConstructor;

/**
 * 登录成功处理
 *
 * @author shaw
 * @date 2023/06/20
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessServiceImpl implements LoginSuccessService {

	@Override
	public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
			AuthInfoResult authInfoResult) {
		//TODO  记录登录日志
	}

}
