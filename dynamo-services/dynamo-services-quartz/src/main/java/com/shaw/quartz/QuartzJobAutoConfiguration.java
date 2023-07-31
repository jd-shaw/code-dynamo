package com.shaw.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author shaw
 * @date 2023/7/5
 */
@ConfigurationPropertiesScan
@EntityScan
@EnableJpaRepositories
@ComponentScan
public class QuartzJobAutoConfiguration {

}
