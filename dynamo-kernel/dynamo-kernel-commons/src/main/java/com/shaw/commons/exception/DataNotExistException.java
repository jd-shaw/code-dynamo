package com.shaw.commons.exception;

import com.shaw.commons.code.CommonErrorCode;

/**
 * 数据不存在异常
 *
 */
public class DataNotExistException extends BaseException {

	public DataNotExistException(String msg) {
		super(CommonErrorCode.DATA_NOT_EXIST, msg);
	}

	public DataNotExistException() {
		super(CommonErrorCode.DATA_NOT_EXIST, "数据不存在");
	}

}
