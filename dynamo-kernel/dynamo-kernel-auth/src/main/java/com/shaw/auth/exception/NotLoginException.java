package com.shaw.auth.exception;

import static com.shaw.commons.code.CommonErrorCode.AUTHENTICATION_FAIL;

import com.shaw.commons.exception.BaseException;

/**
 * 未登录异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class NotLoginException extends BaseException {

	public NotLoginException(String msg) {
		super(AUTHENTICATION_FAIL, msg);
	}

	public NotLoginException() {
		super(AUTHENTICATION_FAIL, "未登录");
	}

}
