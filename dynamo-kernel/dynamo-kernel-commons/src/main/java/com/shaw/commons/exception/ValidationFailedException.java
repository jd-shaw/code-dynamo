package com.shaw.commons.exception;

import java.io.Serializable;

import com.shaw.commons.code.CommonErrorCode;

/**
 * 验证失败异常
 */
public class ValidationFailedException extends BaseException implements Serializable {

	private static final long serialVersionUID = -3973809880035275189L;

	public ValidationFailedException(String detail) {
		super(CommonErrorCode.VALIDATE_PARAMETERS_ERROR, "验证参数错误" + System.lineSeparator() + detail);
	}

	public ValidationFailedException() {
		super();
	}

}
