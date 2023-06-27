package com.shaw.auth.exception;

import com.shaw.commons.exception.BaseException;

/**
 * 登录方式不存在
 *
 * @author shaw
 * @date 2023/06/20
 */
public class LonginTypeNotFoundException extends BaseException {

	public LonginTypeNotFoundException() {
		super("未找到对应的登录方式");
	}

}
