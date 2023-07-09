package com.shaw.auth.handler;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.shaw.auth.saroute.RouterCheck;
import com.shaw.auth.exception.RouterCheckException;
import com.shaw.utils.web.WebServletUtil;

import cn.dev33.satoken.fun.SaFunction;
import cn.dev33.satoken.router.SaRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 鉴权路由配置类
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SaRouteHandler implements InitializingBean {

	private final List<RouterCheck> routerChecks;

	@Override
	public void afterPropertiesSet() {
		// 排序
		routerChecks.sort(Comparator.comparing(RouterCheck::sortNo));
	}

	/**
	 * 路由检查处理方法
	 */
	public SaFunction check(Object handler) {
		return () -> {
			boolean check = routerChecks.stream().anyMatch(routerCheck -> routerCheck.check(handler));
			if (check) {
				SaRouter.stop();
			} else {
				log.warn("{} 没有对应的权限", WebServletUtil.getPath());
				throw new RouterCheckException();
			}
		};
	}

}
