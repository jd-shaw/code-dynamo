package com.shaw.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shaw.auth.entity.AuthInfoResult;

/**
 * 登录成功处理器
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface LoginSuccessService {

	void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, AuthInfoResult authInfoResult);

}
