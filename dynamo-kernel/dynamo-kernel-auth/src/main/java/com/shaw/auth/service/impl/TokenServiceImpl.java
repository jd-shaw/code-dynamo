package com.shaw.auth.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.shaw.commons.annotation.TimeConsuming;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shaw.auth.authentication.AbstractAuthentication;
import com.shaw.auth.configuration.AuthProperties;
import com.shaw.auth.entity.AuthClient;
import com.shaw.auth.entity.AuthInfoResult;
import com.shaw.auth.entity.AuthLoginType;
import com.shaw.auth.entity.LoginAuthContext;
import com.shaw.auth.exception.ClientNotEnableException;
import com.shaw.auth.exception.LoginFailureException;
import com.shaw.auth.service.*;
import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.code.CommonCode;
import com.shaw.commons.entity.UserDetail;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证相关服务
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final GetAuthLoginTypeService getAuthLoginTypeService;
	private final GetAuthClientService getAuthClientService;
	private final AuthProperties authProperties;

	private final List<AbstractAuthentication> abstractAuthentications;
	private final List<LoginSuccessService> loginSuccessHandlers;
	private final List<LoginFailureService> loginFailureHandlers;

	/**
	 * 登录
	 */
	@Override
	@TimeConsuming
	public String login(HttpServletRequest request, HttpServletResponse response) {
		AuthInfoResult authInfoResult;
		AuthLoginType authLoginType = getAuthLoginType(request);
		AuthClient authClient = getAuthApplication(request);
		try {
			LoginAuthContext loginAuthContext = new LoginAuthContext().setRequest(request).setResponse(response)
					.setAuthProperties(getAuthProperties()).setAuthLoginType(authLoginType).setAuthClient(authClient);
			// 校验登录终端
			validateAuthClient(loginAuthContext);
			// 认证并获取结果
			authInfoResult = authentication(loginAuthContext);
			// 登录处理
			login(authInfoResult, loginAuthContext);
		} catch (LoginFailureException e) {
			// 登录失败回调
			this.loginFailureHandler(request, response, e);
			throw e;
		}
		// 登录成功回调
		this.loginSuccessHandler(request, response, authInfoResult);
		return StpUtil.getTokenValue();
	}

	/**
	 * 退出
	 */
	@Override
	public void logout() {
		StpUtil.logout();
	}

	/**
	 * 成功处理
	 */
	private void loginSuccessHandler(HttpServletRequest request, HttpServletResponse response,
			AuthInfoResult authInfoResult) {
		for (LoginSuccessService loginSuccessHandler : getLoginSuccessHandlers()) {
			try {
				loginSuccessHandler.onLoginSuccess(request, response, authInfoResult);
			} catch (Exception ignored) {
			}
		}
	}

	/**
	 * 失败处理
	 */
	private void loginFailureHandler(HttpServletRequest request, HttpServletResponse response,
			LoginFailureException e) {
		for (LoginFailureService loginFailureHandler : getLoginFailureHandlers()) {
			try {
				loginFailureHandler.onLoginFailure(request, response, e);
			} catch (Exception ignored) {
			}
		}
	}

	/**
	 * 获取方式
	 */
	private AuthLoginType getAuthLoginType(HttpServletRequest request) {
		// 登录方式
		AuthLoginType authLoginType = getGetAuthLoginTypeService().getAuthLoginType(SecurityUtil.getLoginType(request));
		if (authLoginType.getEnable() != 1) {
			throw new ClientNotEnableException();
		}
		return authLoginType;
	}

	/**
	 * 获取终端
	 */
	private AuthClient getAuthApplication(HttpServletRequest request) {
		// 终端
		AuthClient authClient = getGetAuthClientService().getAuthApplication(SecurityUtil.getClient(request));
		if (authClient.getEnable() != 1) {
			throw new ClientNotEnableException();
		}
		return authClient;
	}

	/**
	 * 校验该认证应用是否支持此种登录方式
	 */
	private void validateAuthClient(LoginAuthContext loginAuthContext) {
		AuthClient authClient = loginAuthContext.getAuthClient();
		AuthLoginType authLoginType = loginAuthContext.getAuthLoginType();
		if (CollectionUtils.isEmpty(authClient.getLoginTypeIds())
				|| !authClient.getLoginTypeIds().contains(authLoginType.getId())) {
			throw new LoginFailureException("不支持的登录方式");
		}
	}

	/**
	 * 认证
	 */
	private @NotNull AuthInfoResult authentication(LoginAuthContext context) {
		String loginType = context.getAuthLoginType().getCode();
		return getAbstractAuthentications().stream().filter(o -> o.adaptation(loginType)).findFirst()
				.map(o -> o.authentication(context)).orElseThrow(() -> new LoginFailureException("未找到对应的登录认证器"));
	}

	/**
	 * 登录
	 */
	private void login(AuthInfoResult authInfoResult, LoginAuthContext context) {
		AuthLoginType authLoginType = context.getAuthLoginType();
		AuthClient authClient = context.getAuthClient();
		SaLoginModel saLoginModel = new SaLoginModel().setDevice(authClient.getCode())
				.setTimeout(authLoginType.getTimeout() * 60);

		authInfoResult.setClient(authClient.getCode()).setLoginType(authLoginType.getCode());
		StpUtil.login(authInfoResult.getId(), saLoginModel);
		SaSession session = StpUtil.getSession();
		UserDetail userDetail = authInfoResult.getUserDetail();
		session.set(CommonCode.USER, userDetail);
	}

}
