package com.shaw.exception.handler;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 配置
 *
 * @author shaw
 * @date 2023/7/06
 */
@Getter
@Setter
@ConfigurationProperties("dynamo.common.exception")
public class ExceptionHandlerProperties {

	/** 是否显示详细异常信息 */
	private boolean showFullMessage;

}
