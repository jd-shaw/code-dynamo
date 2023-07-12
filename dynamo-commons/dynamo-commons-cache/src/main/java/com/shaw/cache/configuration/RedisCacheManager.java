package com.shaw.cache.configuration;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

import lombok.Setter;

/**
 * 自定义Redis缓存管理
 *
 * @author shaw
 * @date 2023/7/12
 */
public class RedisCacheManager extends org.springframework.data.redis.cache.RedisCacheManager {

	@Setter
	private Map<String, Integer> keysTtl;

	private final RedisCacheWriter cacheWriter;

	public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
		super(cacheWriter, defaultCacheConfiguration);
		this.cacheWriter = cacheWriter;
	}

	public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
			String... initialCacheNames) {
		super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
		this.cacheWriter = cacheWriter;
	}

	public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
			boolean allowInFlightCacheCreation, String... initialCacheNames) {
		super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
		this.cacheWriter = cacheWriter;
	}

	public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
			Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
		super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
		this.cacheWriter = cacheWriter;
	}

	public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
			Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
		super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
		this.cacheWriter = cacheWriter;
	}

	/**
	 * 创建Redis缓存
	 */
	@Override
	@SuppressWarnings({ "ConstantConditions", "NullableProblems" })
	protected org.springframework.data.redis.cache.RedisCache createRedisCache(@Nullable String name,
			@Nullable RedisCacheConfiguration cacheConfig) {
		Optional<String> keyOptional = keysTtl.keySet().stream().sorted((o1, o2) -> StringUtils.compare(o2, o1, false))
				.filter(name::startsWith).findFirst();
		// 是自定义的key
		if (keyOptional.isPresent()) {
			String key = keyOptional.get();
			return this.createBootxRedisCache(name, cacheConfig.entryTtl(Duration.ofSeconds(keysTtl.get(key))));
		}
		return this.createBootxRedisCache(name, cacheConfig);
	}

	/**
	 * 替换为自定义的RedisCache
	 */
	public RedisCache createBootxRedisCache(String name, RedisCacheConfiguration cacheConfig) {
		return new RedisCache(name, this.cacheWriter, cacheConfig);
	}

}
