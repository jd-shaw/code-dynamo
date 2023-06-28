package com.shaw.iam.core.upms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.upms.entity.RoleMenu;

/**
 * 角色权限关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface RoleMenuDao extends JpaRepository<RoleMenu, String> {

	List<RoleMenu> findListByRoleIdAndClientCode(String roleId, String clientCode);

	List<RoleMenu> findByRoleIdIn(List<String> roleIds);

	void deleteByPermissionId(String permissionId);

	void deleteByIdIn(List<String> ids);

	void deleteByRoleId(String roleId);
}
