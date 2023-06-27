package com.shaw.iam.core.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.shaw.auth.exception.LoginFailureException;
import com.shaw.auth.handler.LoginFailureHandler;

import lombok.RequiredArgsConstructor;

/**
 * 登录失败
 *
 * @author shaw
 * @date 2023/06/20
 */
@Component
@RequiredArgsConstructor
public class LoginFailureHandlerImpl implements LoginFailureHandler {

	@Override
	public void onLoginFailure(HttpServletRequest request, HttpServletResponse response, LoginFailureException e) {
		//TODO  记录登录日志
	}

}
