package com.shaw.auth.exception;

/**
 * 用户未找到异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class UserNotFoundException extends LoginFailureException {

	public UserNotFoundException(String username) {
		super(username, "用户未找到");
	}

	public UserNotFoundException() {
		super("用户未找到");
	}

}
