package com.shaw.mysql.jpa;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shaw.mysql.jpa.dao.BaseRepositoryFactoryBean;

/**
 * @author shaw
 * @date 2023/06/20
 */
@ComponentScan
@AutoConfiguration
//@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class MysqlJpaAutoConfiguration {

}
