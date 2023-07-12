package com.shaw.cache.configuration;

import java.util.Objects;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

/**
 * 自定义RedisCache, 缓存值为空不报错
 *
 * @author shaw
 * @date 2023/7/12
 */
public class RedisCache extends org.springframework.data.redis.cache.RedisCache {

	/**
	 * Create new {@link org.springframework.data.redis.cache.RedisCache}.
	 * @param name must not be {@literal null}.
	 * @param cacheWriter must not be {@literal null}.
	 * @param cacheConfig must not be {@literal null}.
	 */
	protected RedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
		super(name, cacheWriter, cacheConfig);
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public void put(Object key, @Nullable Object value) {
		// 允许为空或者非空值
		if (isAllowNullValues() || Objects.nonNull(value)) {
			super.put(key, value);
		}
	}

}
