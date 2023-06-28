package com.shaw.iam.core.upms.service;

import java.util.List;

import com.shaw.iam.dto.upms.RoleMenuDto;

/**
 * @author xjd
 * @date 2023/6/27
 */
public interface RoleMenuService {

	List<RoleMenuDto> findListByRoleIdAndClientCode(String roleId, String clientCode);

	List<RoleMenuDto> findByRoleIds(List<String> roleIds);

}
