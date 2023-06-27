package com.shaw.auth.authentication;

import com.shaw.auth.entity.AuthClient;

/**
 * 获取认证终端
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface GetAuthClientService {

    /**
     * 认证应用
     */
    AuthClient getAuthApplication(String authApplicationCode);

}
