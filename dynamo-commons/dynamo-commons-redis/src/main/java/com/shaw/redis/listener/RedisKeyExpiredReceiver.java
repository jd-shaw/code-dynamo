package com.shaw.redis.listener;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * key过期事件接收
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Component
public class RedisKeyExpiredReceiver extends KeyExpirationEventMessageListener {

	private final List<RedisKeyExpiredListener> redisKeyExpiredListeners;

	public RedisKeyExpiredReceiver(RedisMessageListenerContainer listenerContainer,
			List<RedisKeyExpiredListener> redisKeyExpiredListeners) {
		super(listenerContainer);
		this.redisKeyExpiredListeners = redisKeyExpiredListeners;
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String expiredKey = new String(message.getBody());
		for (RedisKeyExpiredListener redisKeyExpiredListener : redisKeyExpiredListeners) {
			String prefixKey = redisKeyExpiredListener.getPrefixKey();
			if (StringUtils.startsWith(expiredKey, prefixKey)) {
				// 去除key前缀
				redisKeyExpiredListener.onMessage(StringUtils.remove(expiredKey, prefixKey));
			}
		}
	}

}
