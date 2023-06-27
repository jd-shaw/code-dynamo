package com.shaw.iam.exception.user;

import static com.shaw.iam.code.IamErrorCode.USER_PHONE_ALREADY_EXISTED;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;

/**
 * 用户手机已存在异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class UserPhoneAlreadyExistedException extends BaseException implements Serializable {

	private static final long serialVersionUID = -8925952529440870552L;

	public UserPhoneAlreadyExistedException() {
		super(USER_PHONE_ALREADY_EXISTED, "用户手机已存在");
	}

}
