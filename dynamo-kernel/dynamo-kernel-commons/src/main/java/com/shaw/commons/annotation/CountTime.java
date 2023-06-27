package com.shaw.commons.annotation;

import java.lang.annotation.*;

/**
 * 获取程序执行时间注解
 *
 * @author shaw
 * @date 2023/06/20
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CountTime {

}
