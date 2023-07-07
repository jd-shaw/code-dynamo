package com.shaw.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shaw.auth.exception.LoginFailureException;

/**
 * 登录失败处理器
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface LoginFailureHandler {

	void onLoginFailure(HttpServletRequest request, HttpServletResponse response, LoginFailureException e);

}
