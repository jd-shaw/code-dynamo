package com.shaw.monitor.entity;

import java.util.List;
import java.util.Properties;

import com.shaw.commons.rest.dto.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author shaw
 * @date 2023/8/4
 */
@Data
@Schema(title = "Redis监控信息")
public class RedisMonitorResult {

	@Schema(description = "Redis系统信息")
	private Properties info;

	@Schema(description = "命令统计")
	private List<KeyValue> commandStats;

	@Schema(description = "key数量")
	private Long dbSize;

}
