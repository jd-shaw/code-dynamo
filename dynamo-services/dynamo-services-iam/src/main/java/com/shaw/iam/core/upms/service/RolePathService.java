package com.shaw.iam.core.upms.service;

import com.shaw.iam.core.upms.entity.RolePath;
import com.shaw.iam.dto.upms.RolePathDto;

import java.util.List;

/**
 * 角色请求权限关系
 *
 * @author shaw
 * @date 2023/06/20
 */

public interface RolePathService {

	void addRolePath(String roleId, List<String> permissionIds);

	List<String> findIdsByRole(String roleId);

	List<RolePathDto> findIdsByRole(List<String> roleId);

	void deleteByRoleId(String roleId);

	void deleteByPermissionId(String permissionId);
}
