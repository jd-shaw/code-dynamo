package com.shaw.iam.core.permission.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.shaw.commons.rest.dto.BaseDto;
import com.shaw.commons.utils.TreeBuildUtil;
import com.shaw.iam.dto.upms.MenuAndResourceDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.auth.exception.NotLoginException;
import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.entity.UserDetail;
import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.code.PermissionCode;
import com.shaw.iam.core.permission.dao.PermMenuDao;
import com.shaw.iam.core.permission.entity.PermMenu;
import com.shaw.iam.core.permission.service.PermMenuService;
import com.shaw.iam.core.upms.service.RoleMenuService;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.dto.upms.RoleMenuDto;
import com.shaw.iam.param.permission.PermMenuParam;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/6/27
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class PermMenuServiceImpl implements PermMenuService {

	private final PermMenuDao permMenuDao;
	private final RoleMenuService roleMenuService;
	private final UserRoleService userRoleService;

	@Override
	public List<PermMenuDto> findListByClientCode(String clientCode) {
		List<PermMenu> permMenus = getPermMenuDao().findListByClientCode(clientCode);
		return ResultConvertUtil.dtoListConvert(permMenus);
	}

	@Override
	public List<PermMenuDto> findListByIds(List<String> ids) {
		List<PermMenu> permMenus = getPermMenuDao().findAllById(ids);
		return ResultConvertUtil.dtoListConvert(permMenus);
	}

	/**
	 * 添加菜单权限
	 */
	@Override
	@Transactional
	public PermMenuDto add(PermMenuParam param) {
		// 判断是否是一级菜单，是的话清空父菜单
		if (PermissionCode.MENU_TYPE_TOP.equals(param.getMenuType())) {
			param.setParentId(null);
		}
		PermMenu permission = PermMenu.init(param);
		return getPermMenuDao().save(permission).toDto();
	}

	/**
	 * 更新
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_PERM_CODE }, allEntries = true)
	@Transactional
	public PermMenuDto update(PermMenuParam param) {
		PermMenu permMenu = getPermMenuDao().findById(param.getId()).orElseThrow(() -> new BaseException("菜单权限不存在"));
		permMenu.setClientCode(null);
		BeanUtils.copyProperties(param, permMenu);

		// 判断是否是一级菜单，是的话清空父菜单ID
		if (PermissionCode.MENU_TYPE_TOP.equals(permMenu.getMenuType())) {
			permMenu.setParentId(null);
		}
		return getPermMenuDao().save(permMenu).toDto();
	}

	/**
	 * 根据id查询
	 */
	@Override
	public PermMenuDto findById(String id) {
		return getPermMenuDao().findById(id).map(PermMenu::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 列表
	 */
	@Override
	public List<PermMenuDto> findAll() {
		return ResultConvertUtil.dtoListConvert(getPermMenuDao().findAll());
	}

	/**
	 * 列表(根据应用code)
	 */
	@Override
	public List<PermMenuDto> findAllByClientCode(String clientCode) {
		return ResultConvertUtil.dtoListConvert(getPermMenuDao().findListByClientCode(clientCode));
	}

	/**
	 * 根据id集合查询
	 */
	@Override
	public List<PermMenuDto> findByIds(List<String> permissionIds) {
		return ResultConvertUtil.dtoListConvert(getPermMenuDao().findAllById(permissionIds));
	}

	/**
	 * 删除
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_PERM_CODE }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		// 有子菜单不可以删除
		if (getPermMenuDao().existsByParentId(id)) {
			throw new BaseException("有子菜单或下属权限不可以删除");
		}
		getRoleMenuService().deleteByPermissionId(id);
		getPermMenuDao().deleteById(id);
	}

	/**
	 * 根据菜单id获取资源(权限码)列表
	 */
	@Override
	public List<PermMenuDto> findResourceByMenuId(String menuId) {
		UserDetail userDetail = SecurityUtil.getCurrentUser().orElseThrow(NotLoginException::new);
		List<PermMenu> resources = getPermMenuDao().findByParentId(menuId).stream()
				.filter(permMenu -> Objects.equals(permMenu.getMenuType(), PermissionCode.MENU_TYPE_RESOURCE))
				.collect(Collectors.toList());
		// 管理员返回全部
		if (userDetail.isAdmin()) {
			return resources.stream().map(PermMenu::toDto).collect(Collectors.toList());
		}
		// 普通用户只能看到自己有权限的
		List<String> roleIds = getUserRoleService().findRoleIdsByUserId(userDetail.getId());
		List<String> roleMenuIds = getRoleMenuService().findByRoleIds(roleIds).stream()
				.map(RoleMenuDto::getPermissionId).collect(Collectors.toList());
		return resources.stream().filter(permMenu -> roleMenuIds.contains(permMenu.getId())).map(PermMenu::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * 权限编码是否被使用
	 */
	@Override
	public boolean existsByPermCode(String permCode) {
		return permMenuDao.existsByPermCode(permCode);
	}

	/**
	 * 权限编码是否被使用
	 */
	@Override
	public boolean existsByPermCodeAndIdNot(String permCode, String id) {
		return permMenuDao.existsByPermCodeAndIdNot(permCode, id);
	}

	//
	/**
	 * 获取权限树, 包含菜单和资源权限(权限码)
	 */
	@Override
	public List<PermMenuDto> findAllTree(String clientCode) {
		return this.recursiveBuildTree(this.findPermissions(clientCode));
	}

	/**
	 * 获取权限信息列表
	 */
	@Override
	public List<PermMenuDto> findPermissions(String clientCode) {
		UserDetail userDetail = SecurityUtil.getCurrentUser().orElseThrow(NotLoginException::new);
		List<PermMenuDto> permissions;

		// 系统管理员，获取全部的权限
		if (userDetail.isAdmin()) {
			permissions = findListByClientCode(clientCode);
		} else {
			// 非管理员获取自身拥有的权限
			permissions = this.findPermissionsByUser(userDetail.getId()).stream()
					.filter(o -> Objects.equals(clientCode, o.getClientCode())).collect(Collectors.toList());
		}
		return permissions;
	}

	/**
	 * 获取菜单权限树, 不包含资源权限(权限码)
	 */
	@Override
	public List<PermMenuDto> findMenuTree(String clientCode) {
		List<PermMenuDto> permissions = this.findPermissions(clientCode);
		List<PermMenuDto> permissionsByNotButton = permissions.stream()
				.filter(o -> !Objects.equals(PermissionCode.MENU_TYPE_RESOURCE, o.getMenuType()))
				.collect(Collectors.toList());
		return this.recursiveBuildTree(permissionsByNotButton);
	}

	/**
	 * 查询用户查询拥有的权限信息(直接获取所有终端的权限码)
	 */
	@Override
	public List<PermMenuDto> findPermissionsByUser(String userId) {
		List<PermMenuDto> permissions = Lists.newArrayList();

		List<String> roleIds = userRoleService.findRoleIdsByUserId(userId);
		if (CollectionUtils.isEmpty(roleIds)) {
			return permissions;
		}
		List<RoleMenuDto> roleMenus = roleMenuService.findByRoleIds(roleIds);
		List<String> permissionIds = roleMenus.stream().map(RoleMenuDto::getPermissionId).distinct()
				.collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(permissionIds)) {
			permissions = findListByIds(permissionIds);
		}
		return permissions;
	}

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
		List<PermMenuDto> permMenuDtos = this.recursiveBuildTree(menus);
		return new MenuAndResourceDto().setResourcePerms(resourcePerms).setMenus(permMenuDtos);
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
