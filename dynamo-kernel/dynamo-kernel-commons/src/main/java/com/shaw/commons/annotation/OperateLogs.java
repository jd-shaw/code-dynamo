package com.shaw.commons.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解组
 *
 * @author shaw
 * @date 2023/06/20
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperateLogs {

	/** 操作日志注解组 */
	OperateLog[] value();

}
