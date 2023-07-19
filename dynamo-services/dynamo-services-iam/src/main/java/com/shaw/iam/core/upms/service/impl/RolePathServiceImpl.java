package com.shaw.iam.core.upms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.annotation.TimeConsuming;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.core.upms.dao.RolePathDao;
import com.shaw.iam.core.upms.entity.RolePath;
import com.shaw.iam.core.upms.service.RolePathService;
import com.shaw.iam.dto.upms.RolePathDto;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.utils.RandomUIDUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/6/28
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class RolePathServiceImpl implements RolePathService {

	private final RolePathDao rolePathDao;

	@Override
	@Transactional
	@CacheEvict(value = { CachingCode.USER_PATH }, allEntries = true)
	@TimeConsuming
	public void addRolePath(String roleId, List<String> permissionIds) {
		// 先删后增
		List<RolePath> rolePaths = getRolePathDao().findByRoleId(roleId);
		List<String> rolePathIds = rolePaths.stream().map(RolePath::getPermissionId).collect(Collectors.toList());
		// 需要删除的
		List<String> deleteIds = rolePaths.stream()
				.filter(rolePath -> !permissionIds.contains(rolePath.getPermissionId())).map(BaseDomain::getId)
				.collect(Collectors.toList());

		List<RolePath> rolePermissions = permissionIds.stream().filter(id -> !rolePathIds.contains(id))
				.map(permissionId -> new RolePath(RandomUIDUtils.getUID(), SecurityUtil.getUserIdOrDefaultId(), roleId,
						permissionId))
				.collect(Collectors.toList());
		getRolePathDao().deleteAllById(deleteIds);
		getRolePathDao().saveAll(rolePermissions);
	}

	/**
	 * 根据角色id获取关联权限id
	 */
	@Override
	@TimeConsuming
	public List<String> findIdsByRole(String roleId) {
		List<RolePath> rolePermissions = getRolePathDao().findByRoleId(roleId);
		return rolePermissions.stream().map(RolePath::getPermissionId).collect(Collectors.toList());
	}

	@Override
	public List<RolePathDto> findIdsByRole(List<String> roleId) {
		List<RolePath> rolePermissions = getRolePathDao().findByRoleIdIn(roleId);
		return ResultConvertUtil.dtoListConvert(rolePermissions);
	}

	@Override
	public void deleteByRoleId(String roleId) {
		getRolePathDao().deleteByRoleId(roleId);
	}

	@Override
	public void deleteByPermissionId(String permissionId) {
		getRolePathDao().deleteByPermissionId(permissionId);
	}

}
