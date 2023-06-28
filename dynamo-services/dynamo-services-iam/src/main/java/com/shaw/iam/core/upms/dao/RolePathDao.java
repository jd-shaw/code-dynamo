package com.shaw.iam.core.upms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.upms.entity.RolePath;

/**
 * 角色权限
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface RolePathDao extends JpaRepository<RolePath, String> {

	void deleteByRoleId(String roleId);

	void deleteByPermissionId(String permissionId);
}
