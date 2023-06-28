package com.shaw.iam.core.upms.service;

/**
 * 角色请求权限关系
 *
 * @author shaw
 * @date 2023/06/20
 */

public interface RolePathService {

    void deleteByRoleId(String roleId);

    void deleteByPermissionId(String permissionId);
}
