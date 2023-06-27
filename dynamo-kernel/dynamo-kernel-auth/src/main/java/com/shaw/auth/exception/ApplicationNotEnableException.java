package com.shaw.auth.exception;

import com.shaw.commons.exception.BaseException;

/**
 * 应用被停用
 *
 * @author shaw
 * @date 2023/06/20
 */
public class ApplicationNotEnableException extends BaseException {

	public ApplicationNotEnableException() {
		super("指定应用已被停用");
	}

}
