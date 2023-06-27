package com.shaw.auth.exception;

import com.shaw.commons.exception.BaseException;

/**
 * 终端方式被停用
 *
 * @author shaw
 * @date 2023/06/20
 */
public class ClientNotEnableException extends BaseException {

	public ClientNotEnableException() {
		super("指定终端方式已被停用");
	}

}
