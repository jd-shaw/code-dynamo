package com.shaw.commons.annotation;

import java.lang.annotation.*;

/**
 * 数据版本日志
 *
 * @author shaw
 * @date 2023/06/20
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataVersionLog {

	/**
	 * 数据记录的标题
	 */
	String title() default "";

}
