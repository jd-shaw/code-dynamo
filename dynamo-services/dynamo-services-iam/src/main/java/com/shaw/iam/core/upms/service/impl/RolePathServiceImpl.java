package com.shaw.iam.core.upms.service.impl;

import org.springframework.stereotype.Service;

import com.shaw.iam.core.upms.dao.RolePathDao;
import com.shaw.iam.core.upms.service.RolePathService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xjd
 * @date 2023/6/28
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class RolePathServiceImpl implements RolePathService {

	private final RolePathDao rolePathDao;

	@Override
	public void deleteByRoleId(String roleId) {
		getRolePathDao().deleteByRoleId(roleId);
	}

	@Override
	public void deleteByPermissionId(String permissionId) {
		getRolePathDao().deleteByPermissionId(permissionId);
	}
}
