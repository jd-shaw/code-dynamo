package com.shaw.auth.authentication;

/**
 * 路由拦截检查
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface RouterCheck {

    /**
     * 排序
     */
    default int sortNo() {
        return 0;
    }

    /**
     * 路由
     */
    boolean check(Object handler);

}
