package com.shaw.iam.core.upms.service;

import java.util.List;

import com.shaw.iam.dto.upms.RoleMenuDto;
import com.shaw.iam.param.upms.RoleMenuParam;

/**
 * @author shaw
 * @date 2023/6/27
 */
public interface RoleMenuService {

	void save(List<RoleMenuParam> roleMenus);

	List<RoleMenuDto> findListByRoleIdAndClientCode(String roleId, String clientCode);

	List<RoleMenuDto> findByRoleIds(List<String> roleIds);

	void deleteByPermissionId(String permissionId);

	void deleteByIds(List<String> ids);

	void deleteByRoleId(String roleId);

}
