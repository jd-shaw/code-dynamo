package com.shaw.iam.exception.login;

import static com.shaw.iam.code.IamErrorCode.USER_PASSWORD_INVALID;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;

/**
 * 用户密码不正确异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class UserPasswordInvalidException extends BaseException implements Serializable {

    private static final long serialVersionUID = 6321083408077778554L;

    public UserPasswordInvalidException() {
        super(USER_PASSWORD_INVALID, "用户密码不正确");
    }

}
