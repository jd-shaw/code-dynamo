package com.shaw.iam.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.shaw.auth.authentication.RouterCheck;
import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.entity.UserDetail;
import com.shaw.iam.core.upms.service.RolePathService;
import com.shaw.utils.web.WebServletUtil;

import lombok.RequiredArgsConstructor;

/**
 * 用户路径路由拦截
 *
 * @author shaw
 * @date 2023/06/20
 */
@Component
@RequiredArgsConstructor
public class UserPathRouterCheck implements RouterCheck {

	private final RolePathService rolePathService;

	private final AntPathMatcher matcher = new AntPathMatcher();

	@Override
	public int sortNo() {
		return 10;
	}

	@Override
	public boolean check(Object handler) {
		String method = WebServletUtil.getMethod();
		String path = WebServletUtil.getPath();

		Optional<UserDetail> UserDetailOpt = SecurityUtil.getCurrentUser();
		if (!UserDetailOpt.isPresent()) {
			return false;
		}
		UserDetail userDetail = UserDetailOpt.get();
		//		List<String> paths = rolePathService.findSimplePathsByUser(method, userDetail.getId());
		//		return paths.stream().anyMatch(pattern -> matcher.match(pattern, path));

		return false;
	}

}
