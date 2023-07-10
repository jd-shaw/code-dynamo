package com.shaw.iam.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.shaw.auth.saroute.RouterCheck;
import com.shaw.iam.core.permission.service.PermPathService;
import com.shaw.utils.web.WebServletUtil;

import lombok.RequiredArgsConstructor;

/**
 * 跳过不启用权限控制的路径
 *
 * @author shaw
 * @date 2023/06/20
 */
@Component
@RequiredArgsConstructor
public class IgnorePathRouterCheck implements RouterCheck {

	private final PermPathService pathService;

	private final AntPathMatcher matcher = new AntPathMatcher();

	@Override
	public int sortNo() {
		return 2;
	}

	@Override
	public boolean check(Object handler) {
		String method = WebServletUtil.getMethod();
		String path = WebServletUtil.getPath();
		// 获取不启用权限控制的路径
		//		List<String> ignorePaths = pathService.findIgnorePathByRequestType(method);

		//		return ignorePaths.stream().anyMatch(pattern -> matcher.match(pattern, path));

		return false;
	}

}
