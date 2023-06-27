package com.shaw.auth.code;

/**
 * 认证录方式
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface AuthLoginTypeCode {

	// 参数
	/** 第三方平台授权码 */
	String AUTH_CODE = "authCode";

	/**
	 * 访问AuthorizeUrl后回调时带的参数state，用于和请求AuthorizeUrl前的state比较，防止CSRF攻击
	 */
	String STATE = "state";

	/** 成功代码 */
	int SUCCESS = 2000;

	/* 登录方式 */
	/** 账号密码登录(普通) */
	String PASSWORD = "password";

	/** 账号密码登录(GoView) */
	String PASSWORD_GO_VIEW = "passwordGoView";

	/** 手机号登录 */
	String PHONE = "phone";

}
