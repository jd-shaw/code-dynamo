package com.shaw.redis.listener;

/**
 * redis订阅消息
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface RedisTopicListener<T> {

	/**
	 * 订阅主题名称
	 */
	String getTopic();

	/**
	 * 消息处理
	 * @param obj 消息对象
	 */
	void onMessage(T obj);

}
