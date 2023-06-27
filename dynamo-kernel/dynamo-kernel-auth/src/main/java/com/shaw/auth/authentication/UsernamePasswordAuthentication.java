package com.shaw.auth.authentication;

import com.shaw.auth.code.AuthLoginTypeCode;

/**
 * 用户密码认证方式
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UsernamePasswordAuthentication extends AbstractAuthentication {

    /**
     * 账密登录
     */
    @Override
    default String getLoginType() {
        return AuthLoginTypeCode.PASSWORD;
    }

}
