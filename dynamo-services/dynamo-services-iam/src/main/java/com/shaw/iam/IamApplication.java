package com.shaw.iam;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 身份识别与访问管理
 *
 * @author shaw
 * @date 2023/06/20
 */
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class IamApplication {

}
