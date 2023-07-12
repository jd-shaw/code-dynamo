package com.shaw.cache.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * spring cache 配置
 *
 * @author shaw
 * @date 2023/7/12
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "dynamo.common.cache")
public class CachingProperties {

	/** 序列化时是否保留数据类型数据(变更后需要清除历史数据,否则会报错) */
	private boolean retainType = true;

	/** 默认超时时间 30分钟 */
	private Integer defaultTtl = 60 * 30;

	/** 自定义过期时间 秒 */
	private Map<String, Integer> keysTtl = new HashMap<>();

}
