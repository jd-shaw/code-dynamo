package com.shaw.auth.service;

import com.shaw.auth.entity.AuthLoginType;

/**
 * 获取认证登录方式对象服务
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface GetAuthLoginTypeService {

	/**
	 * @param loginType 登录方式编码
	 */
	AuthLoginType getAuthLoginType(String loginType);

}
