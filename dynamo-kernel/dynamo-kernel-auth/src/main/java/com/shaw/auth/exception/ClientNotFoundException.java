package com.shaw.auth.exception;

import com.shaw.commons.exception.BaseException;

/**
 * 终端不存在
 *
 * @author shaw
 * @date 2023/06/20
 */
public class ClientNotFoundException extends BaseException {

	public ClientNotFoundException() {
		super("未找到对应的终端");
	}

}
