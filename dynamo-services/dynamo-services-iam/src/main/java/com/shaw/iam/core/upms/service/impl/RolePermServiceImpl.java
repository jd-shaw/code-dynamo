package com.shaw.iam.core.upms.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.commons.annotation.NestedPermission;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.code.PermissionCode;
import com.shaw.iam.core.permission.service.PermMenuService;
import com.shaw.iam.core.upms.service.RoleMenuService;
import com.shaw.iam.core.upms.service.RolePermService;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.dto.upms.RoleMenuDto;
import com.shaw.iam.param.upms.RoleMenuParam;
import com.shaw.utils.RandomUIDUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/6/27
 */
@Service
@Getter
@RequiredArgsConstructor
public class RolePermServiceImpl implements RolePermService {

	private final RoleMenuService roleMenuService;
	private final UserRoleService userRoleService;
	private final PermMenuService permMenuService;

	/**
	 * 保存角色菜单授权
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_PERM_CODE }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void save(String roleId, String clientCode, List<String> permissionIds) {
		//		 先删后增
		List<RoleMenuDto> RoleMenus = getRoleMenuService().findListByRoleIdAndClientCode(roleId, clientCode);
		List<String> roleMenuIds = RoleMenus.stream().map(RoleMenuDto::getPermissionId).collect(Collectors.toList());
		// 需要删除的
		List<String> deleteIds = RoleMenus.stream()
				.filter(rolePath -> !permissionIds.contains(rolePath.getPermissionId())).map(RoleMenuDto::getId)
				.collect(Collectors.toList());

		List<RoleMenuParam> roleMenus = permissionIds.stream().filter(id -> !roleMenuIds.contains(id))
				.map(permissionId -> new RoleMenuParam(RandomUIDUtils.getUID(), roleId, clientCode, permissionId))
				.collect(Collectors.toList());
		getRoleMenuService().deleteByIds(deleteIds);
		getRoleMenuService().save(roleMenus);
	}

	/**
	 * 根据角色查询对应的权限id
	 */
	@Override
	public List<String> findPermissionIdsByRole(String roleId, String clientCode) {
		List<RoleMenuDto> rolePermissions = getRoleMenuService().findListByRoleIdAndClientCode(roleId, clientCode);
		return rolePermissions.stream().map(RoleMenuDto::getPermissionId).collect(Collectors.toList());
	}

	/**
	 * 获取权限菜单id列表,不包含资源权限(权限码)
	 */
	@Override
	public List<String> findMenuIds(String clientCode) {
		List<PermMenuDto> permissions = getPermMenuService().findPermissions(clientCode);
		return permissions.stream().filter(o -> !Objects.equals(PermissionCode.MENU_TYPE_RESOURCE, o.getMenuType()))
				.map(PermMenuDto::getId).collect(Collectors.toList());
	}

	/**
	 * 获取有效的资源(权限码)列表(后端使用,直接获取所有终端的权限码)
	 */
	@Override
	@Cacheable(value = CachingCode.USER_PERM_CODE, key = "#userId")
	@NestedPermission
	public List<String> findEffectPermCodesByUserId(String userId) {
		// 获取关联的的权限码
		List<PermMenuDto> permissions = getPermMenuService().findPermissionsByUser(userId);
		return permissions.stream().filter(o -> Objects.equals(o.getMenuType(), PermissionCode.MENU_TYPE_RESOURCE))
				.filter(PermMenuDto::isEffect).map(PermMenuDto::getPermCode).collect(Collectors.toList());
	}

}
