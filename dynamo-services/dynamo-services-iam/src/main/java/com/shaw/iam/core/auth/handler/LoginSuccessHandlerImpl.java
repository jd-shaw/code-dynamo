package com.shaw.iam.core.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.shaw.auth.entity.AuthInfoResult;
import com.shaw.auth.handler.LoginSuccessHandler;

import lombok.RequiredArgsConstructor;

/**
 * 登录成功处理
 *
 * @author shaw
 * @date 2023/06/20
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessHandlerImpl implements LoginSuccessHandler {

	@Override
	public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
			AuthInfoResult authInfoResult) {
		//TODO  记录登录日志
	}

}
