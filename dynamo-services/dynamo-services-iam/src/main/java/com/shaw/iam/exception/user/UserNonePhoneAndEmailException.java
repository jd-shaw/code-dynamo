package com.shaw.iam.exception.user;

import static com.shaw.iam.code.IamErrorCode.NONE_PHONE_AND_EMAIL;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;

/**
 * 用户手机号和邮箱不可都为空的异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class UserNonePhoneAndEmailException extends BaseException implements Serializable {

	private static final long serialVersionUID = -6866507370268138197L;

	public UserNonePhoneAndEmailException() {
		super(NONE_PHONE_AND_EMAIL, "用户的电话和电子邮件必须包含一个");
	}

}
