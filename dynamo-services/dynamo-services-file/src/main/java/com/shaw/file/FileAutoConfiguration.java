package com.shaw.file;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 文件管理
 *
 * @author shaw
 * @date 2023/06/20
 */
@ComponentScan
@ConfigurationPropertiesScan
@AutoConfiguration
@EnableMongoRepositories
@EntityScan
@EnableJpaRepositories
public class FileAutoConfiguration {

}
