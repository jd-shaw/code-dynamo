package com.shaw.iam.core.upms.service;

import java.util.List;

/**
 * 角色权限菜单关系
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface RolePermService {

	void save(String roleId, String clientCode, List<String> permissionIds);

	List<String> findPermissionIdsByRole(String roleId, String clientCode);

	List<String> findMenuIds(String clientCode);

	List<String> findEffectPermCodesByUserId(String userId);
}
