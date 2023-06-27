package com.shaw.redis.listener;

/**
 * Key过期事件
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface RedisKeyExpiredListener {

	/**
	 * 要监听的key
	 */
	String getPrefixKey();

	/**
	 * 事件处理
	 * @param key 去除key前缀后的过期key值
	 */
	void onMessage(String key);

}
