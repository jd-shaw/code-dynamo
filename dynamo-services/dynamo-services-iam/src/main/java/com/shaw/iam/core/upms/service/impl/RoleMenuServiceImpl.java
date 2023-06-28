package com.shaw.iam.core.upms.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.core.upms.dao.RoleMenuDao;
import com.shaw.iam.core.upms.entity.RoleMenu;
import com.shaw.iam.core.upms.service.RoleMenuService;
import com.shaw.iam.dto.upms.RoleMenuDto;
import com.shaw.iam.param.upms.RoleMenuParam;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author xjd
 * @date 2023/6/27
 */
@Service
@Getter
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {

	private final RoleMenuDao roleMenuDao;

	@Override
	public void save(List<RoleMenuParam> roleMenus) {
		if (CollectionUtils.isNotEmpty(roleMenus)) {
			List<RoleMenu> menus = Lists.newArrayListWithCapacity(roleMenus.size());
			for (RoleMenuParam roleMenu : roleMenus) {
				menus.add(RoleMenu.init(roleMenu));
			}
			getRoleMenuDao().saveAll(menus);
		}
	}

	@Override
	public void deleteByIds(List<String> ids) {
		getRoleMenuDao().deleteByIdIn(ids);
	}

	@Override
	public List<RoleMenuDto> findListByRoleIdAndClientCode(String roleId, String clientCode) {
		List<RoleMenu> roleMenus = getRoleMenuDao().findListByRoleIdAndClientCode(roleId, clientCode);
		return ResultConvertUtil.dtoListConvert(roleMenus);
	}

	@Override
	public List<RoleMenuDto> findByRoleIds(List<String> roleIds) {
		List<RoleMenu> roleMenus = getRoleMenuDao().findByRoleIdIn(roleIds);
		return ResultConvertUtil.dtoListConvert(roleMenus);
	}

	@Override
	public void deleteByPermissionId(String permissionId) {
		getRoleMenuDao().deleteByPermissionId(permissionId);
	}

	@Override
	public void deleteByRoleId(String roleId) {
		getRoleMenuDao().deleteByRoleId(roleId);
	}
}
