package com.shaw.iam.core.upms.service.impl;

import static com.shaw.iam.code.CachingCode.USER_PATH;
import static com.shaw.iam.code.CachingCode.USER_PERM_CODE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.commons.exception.BaseException;
import com.shaw.iam.core.role.dao.RoleDao;
import com.shaw.iam.core.upms.dao.UserRoleDao;
import com.shaw.iam.core.upms.entity.UserRole;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.core.user.dao.UserInfoDao;
import com.shaw.iam.core.user.entity.UserInfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/6/27
 */
@Getter
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

	private final RoleDao roleDao;

	private final UserInfoDao userInfoDao;

	private final UserRoleDao userroleDao;

	@Override
	public List<String> findRoleIdsByUserId(String userId) {
		List<UserRole> userRoles = userroleDao.findListByUserId(userId);
		if (CollectionUtils.isNotEmpty(userRoles)) {
			return userRoles.stream().map(UserRole::getId).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	/**
	 * 给用户分配角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = { USER_PATH, USER_PERM_CODE }, allEntries = true)
	public void saveAssign(String userId, List<String> roleIds) {
		// 先删除用户拥有的角色
		userroleDao.deleteById(userId);
		// 然后给用户添加角色
		List<UserRole> userRoles = this.createUserRoles(userId, roleIds);
		userroleDao.saveAll(userRoles);
	}

	/**
	 * 给用户分配角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = { USER_PATH, USER_PERM_CODE }, allEntries = true)
	public void saveAssignBatch(List<String> userIds, List<String> roleIds) {
		List<UserInfo> userInfos = userInfoDao.findAllById(userIds);
		if (userInfos.size() != userIds.size()) {
			throw new BaseException("用户数据有问题");
		}
		userroleDao.deleteAllById(userIds);
		List<UserRole> userRoles = userIds.stream().map(userId -> this.createUserRoles(userId, roleIds))
				.flatMap(Collection::stream).collect(Collectors.toList());
		userroleDao.saveAll(userRoles);
	}

	/**
	 * 根据id查询角色id
	 */
	//	public List<String> findRoleIdsByUser(String userId) {
	//		return userroleDao.findAllByUser(userId).stream().map(UserRole::getRoleId).distinct()
	//				.collect(Collectors.toList());
	//	}

	/**
	 * 查询用户所对应的角色
	 */
	//	public List<RoleDto> findRolesByUser(Long userId) {
	//		return ResultConvertUtil.dtoListConvert(roleDao.findById(this.findRoleIdsByUser(userId)));
	//	}

	/**
	 * 创建用户角色关联
	 */
	private List<UserRole> createUserRoles(String userId, List<String> roleIds) {
		return roleIds.stream().map(roleId -> new UserRole().setRoleId(roleId).setUserId(userId))
				.collect(Collectors.toList());
	}

	@Override
	public boolean existsByRoleId(String roleId) {
		return getUserroleDao().existsByRoleId(roleId);
	}
}
