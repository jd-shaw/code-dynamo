package com.shaw.iam.exception.user;

import com.shaw.commons.exception.FatalException;

import static com.shaw.iam.code.IamErrorCode.USER_INFO_NOT_EXISTS;

/**
 * 用户信息不存在异常
 *
 * @author shaw
 * @date 2023/06/20
 */
public class UserInfoNotExistsException extends FatalException {

    public UserInfoNotExistsException() {
        super(USER_INFO_NOT_EXISTS, "用户信息不存在");
    }

}
