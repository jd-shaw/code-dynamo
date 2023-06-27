package com.shaw.auth.cache;

import com.shaw.commons.entity.UserDetail;

/**
 * 会话缓存线程存储
 *
 * @author shaw
 * @date 2023/06/20
 */
public final class SessionCacheLocal {

	private static final ThreadLocal<UserDetail> THREAD_LOCAL = new ThreadLocal<>();

	/**
	 * TTL 设置数据
	 */
	public static void put(UserDetail userDetail) {
		THREAD_LOCAL.set(userDetail);
	}

	/**
	 * 获取TTL中的数据
	 */
	public static UserDetail get() {
		return THREAD_LOCAL.get();
	}

	/**
	 * 清除
	 */
	public static void clear() {
		THREAD_LOCAL.remove();
	}

}
