package com.shaw.auth.saroute;

import org.springframework.stereotype.Component;

import com.shaw.auth.saroute.RouterCheck;
import com.shaw.auth.configuration.AuthProperties;
import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.entity.UserDetail;

import lombok.RequiredArgsConstructor;

/**
 * 超级管理员跳过各种限制
 *
 * @author shaw
 * @date 2023/06/20
 */
@Component
@RequiredArgsConstructor
public class IgnoreAdminUserRouterCheck implements RouterCheck {

	private final AuthProperties authProperties;

	@Override
	public int sortNo() {
		return 0;
	}

	@Override
	public boolean check(Object handler) {
		if (authProperties.isEnableAdmin()) {
			UserDetail userDetail = SecurityUtil.getCurrentUser().orElse(new UserDetail());
			return userDetail.isAdmin();
		}
		return false;
	}

}
