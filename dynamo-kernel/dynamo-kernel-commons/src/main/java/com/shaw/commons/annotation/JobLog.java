package com.shaw.commons.annotation;

import java.lang.annotation.*;

/**
 * 定时任务日志
 *
 * @author shaw
 * @date 2023/06/20
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JobLog {

	/**
	 * 是否记录正常日志
	 */
	boolean log() default true;

	/**
	 * 是否记录异常日志
	 */
	boolean errorLog() default true;

}
