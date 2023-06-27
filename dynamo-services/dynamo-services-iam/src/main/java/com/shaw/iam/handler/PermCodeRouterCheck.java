package com.shaw.iam.handler;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.google.common.collect.Lists;
import com.shaw.auth.authentication.RouterCheck;
import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.annotation.PermCode;
import com.shaw.commons.entity.UserDetail;
import com.shaw.iam.core.upms.service.RolePermService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限码方式请求路径拦截
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PermCodeRouterCheck implements RouterCheck {

	private final RolePermService rolePermService;

	/**
	 * 路由检查
	 */
	@Override
	public boolean check(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// controller上是否加了权限码鉴权注解
			PermCode permCode = handlerMethod.getBeanType().getAnnotation(PermCode.class);
			if (Objects.isNull(permCode)) {
				// 方法上上是否加了跳过鉴权注解
				permCode = handlerMethod.getMethodAnnotation(PermCode.class);
			} else {
				// controller和方法上都加了跳过鉴权注解,以方法上为准
				PermCode annotation = handlerMethod.getMethodAnnotation(PermCode.class);
				if (Objects.nonNull(annotation)) {
					permCode = annotation;
				}
			}
			return this.ignoreAuth(permCode);
		}
		return false;
	}

	/**
	 * 权限码鉴权注解处理
	 */
	private boolean ignoreAuth(PermCode permCode) {
		if (Objects.isNull(permCode)) {
			return false;
		}
		List<String> permCodes = Lists.newArrayList(permCode.value());
		if (CollectionUtils.isEmpty(permCodes)) {
			return false;
		}
		Optional<UserDetail> UserDetailOpt = SecurityUtil.getCurrentUser();
		if (!UserDetailOpt.isPresent()) {
			return false;
		}
//		List<String> userPermCodes = rolePermService.findEffectPermCodesByUserId(UserDetailOpt.get().getId());
//		return userPermCodes.stream().anyMatch(permCodes::contains);
		return false;
	}

}
