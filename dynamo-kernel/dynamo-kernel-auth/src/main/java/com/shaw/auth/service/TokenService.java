package com.shaw.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证相关服务
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface TokenService {

    String login(HttpServletRequest request, HttpServletResponse response);

    void logout();
}
