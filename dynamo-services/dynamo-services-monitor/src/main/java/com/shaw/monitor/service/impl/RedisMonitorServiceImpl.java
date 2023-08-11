package com.shaw.monitor.service.impl;

import com.shaw.commons.rest.dto.KeyValue;
import com.shaw.monitor.entity.RedisMonitorResult;
import com.shaw.monitor.service.RedisMonitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author shaw
 * @date 2023/8/11
 */
@Slf4j
@RequiredArgsConstructor
@Service("redisMonitorService")
public class RedisMonitorServiceImpl implements RedisMonitorService {

	private final RedisTemplate<String, ?> redisTemplate;

	/**
	 * 获取Redis服务信息
	 */
	@Override
	public RedisMonitorResult getRedisInfo() {
		// 系统信息
		Properties properties = redisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
		// 获取当前选定数据库中可用键的总数
		Long dbSize = redisTemplate.execute(RedisServerCommands::dbSize);
		// 命令统计
		Properties commandStats = Optional
				.ofNullable(redisTemplate
						.execute((RedisCallback<Properties>) connection -> connection.info("commandstats")))
				.orElse(new Properties());
		List<KeyValue> keyValues = commandStats.stringPropertyNames().stream().map((key) -> {
			String value = commandStats.getProperty(key);
			return new KeyValue().setKey(StringUtils.remove(key, "cmdstat_"))
					.setValue(StringUtils.substringBetween(value, "calls=", ",usec"));
		}).collect(Collectors.toList());

		RedisMonitorResult result = new RedisMonitorResult();
		result.setInfo(properties);
		result.setDbSize(dbSize);
		result.setCommandStats(keyValues);
		return result;
	}
}
