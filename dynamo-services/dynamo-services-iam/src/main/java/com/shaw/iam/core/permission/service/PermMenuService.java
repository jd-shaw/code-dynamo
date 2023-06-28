package com.shaw.iam.core.permission.service;

import java.util.List;

import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.dto.upms.MenuAndResourceDto;
import com.shaw.iam.param.permission.PermMenuParam;

/**
 * 权限
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface PermMenuService {

	List<PermMenuDto> findListByClientCode(String clientCode);

	List<PermMenuDto> findListByIds(List<String> ids);

	PermMenuDto update(PermMenuParam param);

	PermMenuDto add(PermMenuParam param);

	PermMenuDto findById(String id);

	List<PermMenuDto> findAll();

	List<PermMenuDto> findAllByClientCode(String clientCode);

	List<PermMenuDto> findByIds(List<String> permissionIds);

	void delete(String id);

	List<PermMenuDto> findResourceByMenuId(String menuId);

	boolean existsByPermCode(String permCode);

	boolean existsByPermCodeAndIdNot(String permCode, String id);

	List<PermMenuDto> findAllTree(String clientCode);

	List<PermMenuDto> findPermissions(String clientCode);

	List<PermMenuDto> findMenuTree(String clientCode);

	List<PermMenuDto> findPermissionsByUser(String userId);

	MenuAndResourceDto getPermissions(String clientCode);
}
