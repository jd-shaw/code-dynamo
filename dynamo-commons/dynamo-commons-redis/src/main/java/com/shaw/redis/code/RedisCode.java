package com.shaw.redis.code;

/**
 * redis常量
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface RedisCode {

	/** 发布订阅主题前缀 */
	String TOPIC_PREFIX = "dynamo:redis:topic:";

	/** 监听的消息订阅内容 */
	String TOPIC_PATTERN_TOPIC = "dynamo:redis:topic:**";

}
