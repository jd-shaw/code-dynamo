package com.shaw.commons.annotation;

import java.lang.annotation.*;

/**
 * 大字段注解
 *
 * @author shaw
 * @date 2023/06/20
 */
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BigField {

}
