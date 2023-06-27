package com.shaw.commons.exception;

/**
 * @author xjd
 * @date 2023/06/20
 */
public class ServiceException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String msg, Object... objects) {
		super(String.format(msg, objects));
	}

}
