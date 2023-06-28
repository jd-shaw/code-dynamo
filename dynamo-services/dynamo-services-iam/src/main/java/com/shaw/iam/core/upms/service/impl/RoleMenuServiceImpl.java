package com.shaw.iam.core.upms.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.shaw.iam.core.upms.dao.RoleMenuDao;
import com.shaw.iam.core.upms.entity.RoleMenu;
import com.shaw.iam.core.upms.service.RoleMenuService;
import com.shaw.iam.dto.upms.RoleMenuDto;

import lombok.RequiredArgsConstructor;

/**
 * @author xjd
 * @date 2023/6/27
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {

	private final RoleMenuDao roleMenuDao;

	@Override
	public List<RoleMenuDto> findListByRoleIdAndClientCode(String roleId, String clientCode) {
		List<RoleMenu> roleMenus = roleMenuDao.findListByRoleIdAndClientCode(roleId, clientCode);
		return covert(roleMenus);
	}

	@Override
	public List<RoleMenuDto> findByRoleIds(List<String> roleIds) {
		List<RoleMenu> roleMenus = roleMenuDao.findByRoleIdIn(roleIds);
		return covert(roleMenus);
	}

	private List<RoleMenuDto> covert(List<RoleMenu> roleMenus) {
		if (CollectionUtils.isNotEmpty(roleMenus)) {
			List<RoleMenuDto> roleMenuDtos = Lists.newArrayListWithExpectedSize(roleMenus.size());
			for (RoleMenu roleMenu : roleMenus) {
				roleMenuDtos.add(roleMenu.toDto());
			}
			return roleMenuDtos;
		}
		return Collections.emptyList();
	}
}
