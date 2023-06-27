package com.shaw.redis.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaw.commons.exception.FatalException;
import com.shaw.redis.RedisClient;
import com.shaw.redis.code.RedisCode;
import com.shaw.redis.listener.RedisTopicReceiver;

import lombok.RequiredArgsConstructor;

/**
 * Redis配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@Configuration
@ConditionalOnClass(StringRedisTemplate.class)
@RequiredArgsConstructor
public class RedisAutoConfiguration {

	private final ObjectMapper objectMapper;

	/**
	 * 默认 RedisClient
	 */
	@Bean
	@Primary
	public RedisClient redisClient(StringRedisTemplate stringRedisTemplate,
			RedisTemplate<String, Object> redisTemplates) {
		return new RedisClient(stringRedisTemplate, redisTemplates);
	}

	/**
	 * 默认 RedisTemplate
	 */
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Bean
	@Primary
	public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory,
			@Qualifier("typeObjectMapper") ObjectMapper typeObjectMapper) {

		// 配置key和value的序列化方式
		StringRedisSerializer keySerializer = new StringRedisSerializer();
		RedisSerializer<?> valueSerializer = new GenericJackson2JsonRedisSerializer(typeObjectMapper);

		// 构建RedisTemplate
		RedisTemplate<String, ?> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(keySerializer);
		template.setHashKeySerializer(keySerializer);
		template.setValueSerializer(valueSerializer);
		template.setHashValueSerializer(valueSerializer);
		template.afterPropertiesSet();
		return template;

	}

	/**
	 * 配置lettuce连接池 将yml的配置数据写到这个bean里
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
	public GenericObjectPoolConfig<LettucePoolingClientConfiguration> redisPool() {
		return new GenericObjectPoolConfig<>();
	}

	/**
	 * 默认redis配置
	 */
	@Bean
	@Primary
	public RedisConfiguration redisConfiguration(RedisProperties redisProperties) {
		// 单机模式/集群模式
		RedisProperties.Cluster cluster = redisProperties.getCluster();
		if (cluster == null || CollectionUtils.isEmpty(cluster.getNodes())) {
			RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
			configuration.setHostName(redisProperties.getHost());
			configuration.setDatabase(redisProperties.getDatabase());
			configuration.setPort(redisProperties.getPort());
			configuration.setPassword(redisProperties.getPassword());
			return configuration;
		} else {
			RedisClusterConfiguration configuration = new RedisClusterConfiguration();
			List<RedisNode> redisNodes = cluster.getNodes().stream().map(node -> {
				String[] hostAndPort = StringUtils.split(node, ':');
				if (hostAndPort.length != 2) {
					throw new FatalException(1, "Redis Cluster集群配置错误, 无法启动");
				}
				return new RedisNode(hostAndPort[0], Integer.parseInt(hostAndPort[0]));
			}).collect(Collectors.toList());
			configuration.setClusterNodes(redisNodes);
			configuration.setPassword(redisProperties.getPassword());
			configuration.setMaxRedirects(cluster.getMaxRedirects());
			return configuration;
		}
	}

	/**
	 * Lettuce连接工厂
	 */
	@Bean
	@Primary
	public LettuceConnectionFactory factory(GenericObjectPoolConfig<LettucePoolingClientConfiguration> config,
			RedisConfiguration redisConfiguration) {
		LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config)
				.build();
		return new LettuceConnectionFactory(redisConfiguration, clientConfiguration);
	}

	/**
	 * redis 消息监听容器
	 */
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
			RedisTopicReceiver redisTopicReceiver, ObjectMapper typeObjectMapper) {
		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);

		// 消息订阅配置
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(redisTopicReceiver);
		// 设置序列化方式
		messageListenerAdapter.setSerializer(new GenericJackson2JsonRedisSerializer(typeObjectMapper));
		redisMessageListenerContainer.addMessageListener(messageListenerAdapter,
				new PatternTopic(RedisCode.TOPIC_PATTERN_TOPIC));
		return redisMessageListenerContainer;
	}

}
