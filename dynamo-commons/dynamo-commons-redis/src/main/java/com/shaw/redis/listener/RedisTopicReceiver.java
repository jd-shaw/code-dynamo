package com.shaw.redis.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaw.commons.exception.FatalException;
import com.shaw.redis.code.RedisCode;

import lombok.extern.slf4j.Slf4j;

/**
 * redis订阅消息接收
 *
 * @author shaw
 * @date 2023/06/20
 */
@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class RedisTopicReceiver implements MessageListener {

	private final List<RedisTopicListener> redisTopicListeners;

	private final ObjectMapper objectMapper;

	public RedisTopicReceiver(List<RedisTopicListener> redisTopicListeners,
			@Qualifier("typeObjectMapper") ObjectMapper objectMapper) {
		this.redisTopicListeners = redisTopicListeners;
		this.objectMapper = objectMapper;
	}

	/**
	 * 消息监听
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String topic = new String(message.getChannel());
		for (RedisTopicListener redisTopicListener : redisTopicListeners) {
			if (topic.equals(RedisCode.TOPIC_PREFIX + redisTopicListener.getTopic())) {
				String json = new String(message.getBody());
				Object o;
				try {
					o = objectMapper.readValue(json, Object.class);
				} catch (JsonProcessingException e) {
					log.warn(e.getMessage(), e);
					throw new FatalException(1, e.getMessage());
				}
				// noinspection unchecked
				redisTopicListener.onMessage(o);
			}
		}
	}

}
