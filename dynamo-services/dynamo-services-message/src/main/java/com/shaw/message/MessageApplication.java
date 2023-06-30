package com.shaw.message;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 消息
 *
 * @author shaw
 * @date 2023/06/30
 */
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class MessageApplication {

}
