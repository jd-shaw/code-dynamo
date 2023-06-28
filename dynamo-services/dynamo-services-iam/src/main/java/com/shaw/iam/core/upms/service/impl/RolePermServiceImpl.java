package com.shaw.iam.core.upms.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.shaw.auth.exception.NotLoginException;
import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.entity.UserDetail;
import com.shaw.commons.rest.dto.BaseDto;
import com.shaw.commons.utils.TreeBuildUtil;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.code.PermissionCode;
import com.shaw.iam.core.permission.service.PermMenuService;
import com.shaw.iam.core.upms.service.RoleMenuService;
import com.shaw.iam.core.upms.service.RolePermService;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.dto.upms.MenuAndResourceDto;
import com.shaw.iam.dto.upms.RoleMenuDto;

import lombok.RequiredArgsConstructor;

/**
 * @author xjd
 * @date 2023/6/27
 */
@Service
@RequiredArgsConstructor
public class RolePermServiceImpl implements RolePermService {

	private final RoleMenuService roleMenuService;
	private final UserRoleService userRoleService;
	private final PermMenuService permMenuService;

	/**
	 * 保存角色菜单授权
	 */
	@CacheEvict(value = { CachingCode.USER_PERM_CODE }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void save(String roleId, String clientCode, List<Long> permissionIds) {
		// 先删后增
		//		List<RoleMenuDto> RoleMenus = roleMenuService.findListByRoleIdAndClientCode(roleId, clientCode);
		//		List<Long> roleMenuIds = RoleMenus.stream().map(RoleMenu::getPermissionId).collect(Collectors.toList());
		//		// 需要删除的
		//		List<Long> deleteIds = RoleMenus.stream()
		//				.filter(rolePath -> !permissionIds.contains(rolePath.getPermissionId())).map(MpIdEntity::getId)
		//				.collect(Collectors.toList());
		//
		//		List<RoleMenu> roleMenus = permissionIds.stream().filter(id -> !roleMenuIds.contains(id))
		//				.map(permissionId -> new RoleMenu(roleId, clientCode, permissionId)).collect(Collectors.toList());
		//		roleMenuManager.deleteByIds(deleteIds);
		//		roleMenuManager.saveAll(roleMenus);
	}

	/**
	 * 根据角色查询对应的权限id
	 */
	//	public List<Long> findPermissionIdsByRole(Long roleId, String clientCode) {
	//		List<RoleMenu> rolePermissions = roleMenuManager.findAllByRoleAndClientCode(roleId, clientCode);
	//		return rolePermissions.stream().map(RoleMenu::getPermissionId).collect(Collectors.toList());
	//	}
	//
	//	/**
	//	 * 获取菜单权限树, 不包含资源权限(权限码)
	//	 */
	//	public List<PermMenuDto> findMenuTree(String clientCode) {
	//		List<PermMenuDto> permissions = this.findPermissions(clientCode);
	//		List<PermMenuDto> permissionsByNotButton = permissions.stream()
	//				.filter(o -> !Objects.equals(PermissionCode.MENU_TYPE_RESOURCE, o.getMenuType()))
	//				.collect(Collectors.toList());
	//		return this.recursiveBuildTree(permissionsByNotButton);
	//	}
	//
	//	/**
	//	 * 获取权限树, 包含菜单和资源权限(权限码)
	//	 */
	//	public List<PermMenuDto> findAllTree(String clientCode) {
	//		return this.recursiveBuildTree(this.findPermissions(clientCode));
	//	}
	//
	//	/**
	//	 * 获取权限菜单id列表,不包含资源权限(权限码)
	//	 */
	//	public List<Long> findMenuIds(String clientCode) {
	//		List<PermMenuDto> permissions = this.findPermissions(clientCode);
	//		return permissions.stream().filter(o -> !Objects.equals(PermissionCode.MENU_TYPE_RESOURCE, o.getMenuType()))
	//				.map(PermMenuDto::getId).collect(Collectors.toList());
	//	}

	/**
	 * 获取菜单和资源权限(权限码)
	 */
	@Override
	public MenuAndResourceDto getPermissions(String clientCode) {
		List<PermMenuDto> permissions = this.findPermissions(clientCode);
		List<String> resourcePerms = permissions.stream()
				.filter(o -> Objects.equals(PermissionCode.MENU_TYPE_RESOURCE, o.getMenuType()))
				.filter(PermMenuDto::isEffect).map(PermMenuDto::getPermCode).collect(Collectors.toList());
		List<PermMenuDto> menus = permissions.stream()
				.filter(o -> !Objects.equals(PermissionCode.MENU_TYPE_RESOURCE, o.getMenuType()))
				.collect(Collectors.toList());
		return new MenuAndResourceDto().setResourcePerms(resourcePerms).setMenus(this.recursiveBuildTree(menus));
	}

	/**
	 * 获取权限信息列表
	 */
	private List<PermMenuDto> findPermissions(String clientCode) {
		UserDetail userDetail = SecurityUtil.getCurrentUser().orElseThrow(NotLoginException::new);
		List<PermMenuDto> permissions;

		// 系统管理员，获取全部的权限
		if (userDetail.isAdmin()) {
			permissions = permMenuService.findListByClientCode(clientCode);
		} else {
			// 非管理员获取自身拥有的权限
			permissions = this.findPermissionsByUser(userDetail.getId()).stream()
					.filter(o -> Objects.equals(clientCode, o.getClientCode())).collect(Collectors.toList());
		}
		return permissions;
	}

	//
	//	/**
	//	 * 获取有效的资源(权限码)列表(后端使用,直接获取所有终端的权限码)
	//	 */
	//	@Cacheable(value = USER_PERM_CODE, key = "#userId")
	//	@NestedPermission
	//	public List<String> findEffectPermCodesByUserId(Long userId) {
	//		// 获取关联的的权限码
	//		List<PermMenuDto> permissions = this.findPermissionsByUser(userId);
	//		return permissions.stream().filter(o -> Objects.equals(o.getMenuType(), PermissionCode.MENU_TYPE_RESOURCE))
	//				.filter(PermMenuDto::isEffect).map(PermMenuDto::getPermCode).collect(Collectors.toList());
	//	}
	//
	/**
	 * 查询用户查询拥有的权限信息(直接获取所有终端的权限码)
	 */
	private List<PermMenuDto> findPermissionsByUser(String userId) {
		List<PermMenuDto> permissions = Lists.newArrayList();

		List<String> roleIds = userRoleService.findRoleIdsByUserId(userId);
		if (CollectionUtils.isEmpty(roleIds)) {
			return permissions;
		}
		List<RoleMenuDto> roleMenus = roleMenuService.findByRoleIds(roleIds);
		List<String> permissionIds = roleMenus.stream().map(RoleMenuDto::getPermissionId).distinct()
				.collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(permissionIds)) {
			permissions = permMenuService.findListByIds(permissionIds);
		}
		return permissions;
	}

	/**
	 * 递归建树
	 * @param permissions 查询出的菜单数据
	 * @return 递归后的树列表
	 */
	private List<PermMenuDto> recursiveBuildTree(List<PermMenuDto> permissions) {
		return TreeBuildUtil.build(permissions, null, BaseDto::getId, PermMenuDto::getParentId,
				PermMenuDto::setChildren, Comparator.comparingDouble(PermMenuDto::getSortNo));
	}

}
