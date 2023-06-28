package com.shaw.iam.core.permission.service.impl;

import static com.shaw.iam.code.CachingCode.USER_PERM_CODE;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.PermissionCode;
import com.shaw.iam.core.permission.dao.PermMenuDao;
import com.shaw.iam.core.permission.entity.PermMenu;
import com.shaw.iam.core.permission.service.PermMenuService;
import com.shaw.iam.core.upms.dao.RoleMenuDao;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.param.permission.PermMenuParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xjd
 * @date 2023/6/27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermMenuServiceImpl implements PermMenuService {

	private final PermMenuDao permMenuDao;
	private final RoleMenuDao roleMenuDao;
	private final UserRoleService userRoleService;

	@Override
	public List<PermMenuDto> findListByClientCode(String clientCode) {
		List<PermMenu> permMenus = permMenuDao.findListByClientCode(clientCode);
		return covert(permMenus);
	}

	@Override
	public List<PermMenuDto> findListByIds(List<String> ids) {
		List<PermMenu> permMenus = permMenuDao.findAllById(ids);
		return covert(permMenus);
	}

	private List<PermMenuDto> covert(List<PermMenu> permMenus) {
		if (CollectionUtils.isNotEmpty(permMenus)) {
			List<PermMenuDto> permMenuDtos = Lists.newArrayListWithExpectedSize(permMenus.size());
			for (PermMenu permMenu : permMenus) {
				permMenuDtos.add(permMenu.toDto());
			}
			return permMenuDtos;
		}
		return Collections.emptyList();
	}

	/**
	 * 添加菜单权限
	 */
	@Transactional(rollbackFor = Exception.class)
	public PermMenuDto add(PermMenuParam param) {
		// 判断是否是一级菜单，是的话清空父菜单
		if (PermissionCode.MENU_TYPE_TOP.equals(param.getMenuType())) {
			param.setParentId(null);
		}
		PermMenu permission = PermMenu.init(param);
		return permMenuDao.save(permission).toDto();
	}

	/**
	 * 更新
	 */
	@CacheEvict(value = { USER_PERM_CODE }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public PermMenuDto update(PermMenuParam param) {
		PermMenu permMenu = permMenuDao.findById(param.getId()).orElseThrow(() -> new BaseException("菜单权限不存在"));
		permMenu.setClientCode(null);
		BeanUtils.copyProperties(param, permMenu);

		// 判断是否是一级菜单，是的话清空父菜单ID
		if (PermissionCode.MENU_TYPE_TOP.equals(permMenu.getMenuType())) {
			permMenu.setParentId(null);
		}
		return permMenuDao.save(permMenu).toDto();
	}

	/**
	 * 根据id查询
	 */
	public PermMenuDto findById(String id) {
		return permMenuDao.findById(id).map(PermMenu::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 列表
	 */
	public List<PermMenuDto> findAll() {
		return ResultConvertUtil.dtoListConvert(permMenuDao.findAll());
	}

	/**
	 * 列表(根据应用code)
	 */
	//    public List<PermMenuDto> findAllByClientCode(String clientCode) {
	//        return ResultConvertUtil.dtoListConvert(permMenuDao.findAllByClientCode(clientCode));
	//    }

	/**
	 * 根据id集合查询
	 */
	public List<PermMenuDto> findByIds(List<String> permissionIds) {
		return ResultConvertUtil.dtoListConvert(permMenuDao.findAllById(permissionIds));
	}

	/**
	 * 删除
	 */
	@CacheEvict(value = { USER_PERM_CODE }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		// 有子菜单不可以删除
		//        if (permMenuDao.existsByParentId(id)) {
		//            throw new BaseException("有子菜单或下属权限不可以删除");
		//        }
		//        permMenuDao.deleteByPermission(id);
		//        permMenuDao.deleteById(id);
	}

	/**
	 * 根据菜单id获取资源(权限码)列表
	 */
	//    public List<PermMenuDto> findResourceByMenuId(Long menuId) {
	//        UserDetail userDetail = SecurityUtil.getCurrentUser().orElseThrow(NotLoginException::new);
	//        List<PermMenu> resources = permMenuDao.findAllByParentId(menuId)
	//            .stream()
	//            .filter(permMenu -> Objects.equals(permMenu.getMenuType(), PermissionCode.MENU_TYPE_RESOURCE))
	//            .collect(Collectors.toList());
	//        // 管理员返回全部
	//        if (userDetail.isAdmin()) {
	//            return resources.stream().map(PermMenu::toDto).collect(Collectors.toList());
	//        }
	//        // 普通用户只能看到自己有权限的
	//        List<Long> roleIds = userRoleService.findRoleIdsByUser(userDetail.getId());
	//        List<Long> roleMenuIds = permMenuDao.findAllByRoles(roleIds)
	//            .stream()
	//            .map(RoleMenu::getPermissionId)
	//            .collect(Collectors.toList());
	//        return resources.stream()
	//            .filter(permMenu -> roleMenuIds.contains(permMenu.getId()))
	//            .map(PermMenu::toDto)
	//            .collect(Collectors.toList());
	//    }

	/**
	 * 权限编码是否被使用
	 */
	//    public boolean existsByPermCode(String permCode) {
	//        return permMenuDao.existsByPermCode(permCode);
	//    }

	/**
	 * 权限编码是否被使用
	 * @param id
	 * @return
	 */
	//    public boolean existsByPermCode(String permCode, Long id) {
	//        return permMenuDao.existsByPermCode(permCode, id);
	//    }

}
