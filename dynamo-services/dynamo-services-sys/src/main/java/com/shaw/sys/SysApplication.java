package com.shaw.sys;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 系统模块功能
 *
 * @author shaw
 * @date 2023/06/28
 */
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class SysApplication {

}
