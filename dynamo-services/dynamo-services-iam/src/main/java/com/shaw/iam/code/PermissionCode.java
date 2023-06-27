package com.shaw.iam.code;

/**
 * 权限相关编码
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface PermissionCode {

    /**
     * 0：顶级菜单
     */
    Integer MENU_TYPE_TOP = 0;

    /**
     * 1：子菜单
     */
    Integer MENU_TYPE_SUB = 1;

    /**
     * 2：资源权限 权限码
     */
    Integer MENU_TYPE_RESOURCE = 2;

}
