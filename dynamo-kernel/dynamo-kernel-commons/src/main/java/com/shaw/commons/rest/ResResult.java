package com.shaw.commons.rest;

import java.io.Serializable;

import com.shaw.commons.code.CommonCode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 响应包装类
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Setter
@ToString
public class ResResult<T> implements Serializable {

	private static final long serialVersionUID = -3041700282408360384L;

	private String msg = "success";

	private int code = CommonCode.SUCCESS_CODE;

	private T data;

	public ResResult() {
		super();
	}

	public ResResult(int code) {
		this.code = code;
	}

	public ResResult(int code, T data) {
		this(code);
		this.data = data;
	}

	public ResResult(int code, String msg) {
		this(code);
		this.msg = msg;
	}

	public ResResult(int code, T data, String msg) {
		this(code, msg);
		this.data = data;
	}

}
