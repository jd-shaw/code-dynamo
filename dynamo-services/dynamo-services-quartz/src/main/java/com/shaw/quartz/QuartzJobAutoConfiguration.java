package com.shaw.quartz;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author shaw
 * @date 2023/7/5
 */
@ConfigurationPropertiesScan
@AutoConfiguration
@EntityScan
@EnableJpaRepositories
public class QuartzJobAutoConfiguration {

}
