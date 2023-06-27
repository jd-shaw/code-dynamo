package com.shaw.auth.exception;

import com.shaw.commons.exception.BaseException;

/**
 * 应用不存在
 *
 * @author shaw
 * @date 2023/06/20
 */
public class ApplicationNotFoundException extends BaseException {

	public ApplicationNotFoundException() {
		super("未找到对应的应用");
	}

}
