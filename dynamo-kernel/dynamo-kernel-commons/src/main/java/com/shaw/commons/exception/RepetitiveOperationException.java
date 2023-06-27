package com.shaw.commons.exception;

import java.io.Serializable;

import com.shaw.commons.code.CommonErrorCode;

/**
 * 重复操作异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class RepetitiveOperationException extends SystemException implements Serializable {

	private static final long serialVersionUID = 2120383728758502943L;

	public RepetitiveOperationException() {
		super(CommonErrorCode.REPETITIVE_OPERATION_ERROR, "重复操作异常");
	}

	public RepetitiveOperationException(String msg) {
		super(CommonErrorCode.REPETITIVE_OPERATION_ERROR, msg);
	}

}
