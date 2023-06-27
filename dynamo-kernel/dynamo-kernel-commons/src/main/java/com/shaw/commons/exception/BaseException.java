package com.shaw.commons.exception;

import java.io.Serializable;

import com.shaw.commons.code.CommonCode;

/**
 * 业务异常基类
 */
public class BaseException extends ErrorCodeRuntimeException implements Serializable {

	private static final long serialVersionUID = -3958262511648424790L;

	public BaseException(int code, String message) {
		super(code, message);
	}

	public BaseException(String message) {
		super(CommonCode.FAIL_CODE, message);
	}

	public BaseException() {
		super();
	}

}
