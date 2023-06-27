package com.shaw.auth.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.shaw.auth.configuration.AuthProperties;
import com.shaw.commons.entity.UserDetail;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 认证上下文
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Setter
@Accessors(chain = true)
public class LoginAuthContext {

	/** 请求 */
	@NotNull
	private HttpServletRequest request;

	/** 响应 */
	@NotNull
	private HttpServletResponse response;

	/** 认证终端信息 */
	@NotNull
	private AuthClient authClient;

	/** 登录方式 */
	@NotNull
	private AuthLoginType authLoginType;

	/** 用户对象 */
	private UserDetail userDetail;

	/** 认证参数配置 */
	@NotNull
	private AuthProperties authProperties;

}
