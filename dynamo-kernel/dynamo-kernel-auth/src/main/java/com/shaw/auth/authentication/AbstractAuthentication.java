package com.shaw.auth.authentication;

import java.util.Objects;

import com.shaw.auth.configuration.AuthProperties;
import com.shaw.auth.entity.AuthClient;
import com.shaw.auth.entity.AuthInfoResult;
import com.shaw.auth.entity.LoginAuthContext;
import com.shaw.auth.exception.LoginFailureException;

import com.shaw.commons.entity.UserDetail;

/**
 * 抽象认证器
 *
 */
public interface AbstractAuthentication {

    /**
     * 获取终端编码
     */
    String getLoginType();

    /**
     * 登录类型是否匹配
     */
    default boolean adaptation(String loginType) {
        return Objects.equals(getLoginType(), loginType);
    }

    /**
     * 认证前操作
     */
    default void authenticationBefore(LoginAuthContext context) {

    }

    /**
     * 尝试认证, 必须重写
     */
    AuthInfoResult attemptAuthentication(LoginAuthContext context);

    /**
     * 认证后处理
     */
    default void authenticationAfter(AuthInfoResult authInfoResult, LoginAuthContext context) {

    }

    /**
     * 认证流程
     */
    default AuthInfoResult authentication(LoginAuthContext context) {
        this.authenticationBefore(context);
        // 认证逻辑
        AuthInfoResult authInfoResult = this.attemptAuthentication(context);
        AuthClient authClient = context.getAuthClient();
        // 添加用户信息到上下文中
        UserDetail userDetail = authInfoResult.getUserDetail();
        context.setUserDetail(userDetail);

        // 判断是否开启了超级管理员
        AuthProperties authProperties = context.getAuthProperties();
        if (!authProperties.isEnableAdmin() && userDetail.isAdmin()) {
            throw new LoginFailureException("未开启超级管理员权限");
        }
        // 管理员跳过各种限制
        if (!userDetail.isAdmin()) {
            // 判断用户是否拥有认证应用的权限
            if (!userDetail.getAppIds().contains(authClient.getId())) {
                throw new LoginFailureException("该用户不拥有该终端的权限");
            }
        }
        authenticationAfter(authInfoResult, context);
        return authInfoResult;
    }

}
