package com.shaw.auth.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.Nullable;

import com.shaw.auth.cache.SessionCacheLocal;
import com.shaw.auth.exception.NotLoginException;
import com.shaw.commons.code.CommonCode;
import com.shaw.commons.entity.UserDetail;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpUtil;
import lombok.experimental.UtilityClass;

/**
 * 安全工具类
 *
 * @author shaw
 * @date 2023/06/20
 */
@UtilityClass
public class SecurityUtil {

	private final String LOGIN_TYPE_PARAMETER = "loginType";

	private final String CLIENT_PARAMETER = "client";

	/**
	 * 获取当前用户,无异常
	 * @return 当前登录用户
	 */
	public Optional<UserDetail> getCurrentUser() {
		return getCurrentUser0();
	}

	/**
	 * 获取当前用户,无异常
	 * @return 当前登录用户的id,如未登录
	 */
	public String getUserIdOrDefaultId() {
		return getCurrentUser().map(UserDetail::getId).orElse(null);
	}

	/**
	 * 获取用户id
	 * @throws NotLoginException 未登录异常
	 */
	public String getUserId() {
		return getCurrentUser().map(UserDetail::getId).orElseThrow(NotLoginException::new);
	}

	/**
	 * 获取用户
	 * @throws NotLoginException 未登录异常
	 */
	public UserDetail getUser() {
		return getCurrentUser().orElseThrow(NotLoginException::new);
	}

	/**
	 * 获取登录方式 异步环境中获取会有问题
	 */
	@Nullable
	public String getLoginType(HttpServletRequest request) {
		return request.getParameter(LOGIN_TYPE_PARAMETER);
	}

	/**
	 * 获取终端Code 异步环境中获取会有问题
	 */
	@Nullable
	public String getClient(HttpServletRequest request) {
		return request.getParameter(CLIENT_PARAMETER);
	}

	/**
	 * 获取当前用户,无异常, 使用线程缓存来减少redis的访问频率
	 */
	private Optional<UserDetail> getCurrentUser0() {
		Optional<UserDetail> userDetail = Optional.ofNullable(SessionCacheLocal.get());
		if (!userDetail.isPresent()) {
			try {
				userDetail = Optional.ofNullable(StpUtil.getSession())
						.map(saSession -> saSession.getModel(CommonCode.USER, UserDetail.class));
				SessionCacheLocal.put(userDetail.orElse(null));
			} catch (SaTokenException e) {
				userDetail = Optional.empty();
			}
		}
		return userDetail;
	}

}
