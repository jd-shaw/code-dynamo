package com.shaw.auth.exception;

import com.shaw.commons.exception.BaseException;

/**
 * 路径检查异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class RouterCheckException extends BaseException {

	public RouterCheckException() {
		super("没有对应请求路径的权限");
	}

}
