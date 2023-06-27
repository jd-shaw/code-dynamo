package com.shaw.auth.exception;

import com.shaw.commons.exception.BaseException;

import lombok.Getter;

/**
 * 登录错误异常
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
public class LoginFailureException extends BaseException {

	private final String username;

	public LoginFailureException(String message) {
		super(message);
		this.username = "未知";
	}

	public LoginFailureException(String username, String message) {
		super(message);
		this.username = username;
	}

}
