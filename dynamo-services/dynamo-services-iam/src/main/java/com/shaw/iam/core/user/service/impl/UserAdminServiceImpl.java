package com.shaw.iam.core.user.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.shaw.auth.util.PasswordEncoder;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.code.UserStatusCode;
import com.shaw.iam.core.client.service.ClientService;
import com.shaw.iam.core.dept.service.DeptService;
import com.shaw.iam.core.role.service.RoleService;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.core.user.service.UserAdminService;
import com.shaw.iam.core.user.service.UserDeptService;
import com.shaw.iam.core.user.service.UserExpandInfoService;
import com.shaw.iam.core.user.service.UserInfoService;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.dto.user.UserExpandInfoDto;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.dto.user.UserInfoWhole;
import com.shaw.iam.param.user.UserInfoParam;
import com.shaw.sys.core.service.CaptchaService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/6/29
 */
@Getter
@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

	private final UserInfoService userInfoService;
	private final UserExpandInfoService userExpandInfoService;
	private final UserRoleService userRoleService;
	private final RoleService roleService;
	private final UserDeptService userDeptService;
	private final DeptService deptService;
	private final PasswordEncoder passwordEncoder;
	private final CaptchaService captchaService;
	private final ClientService clientService;
	private final ApplicationEventPublisher eventPublisher;

	/**
	 * 分页查询
	 */
	@Override
	public PageResult<UserInfoDto> page(PageParam pageParam, UserInfoParam userInfoParam) {
		return getUserInfoService().page(pageParam, userInfoParam);
	}

	/**
	 * 锁定用户
	 */
	@Override
	public void lock(String userId) {
		getUserInfoService().updateStatus(userId, UserStatusCode.BAN);
	}

	/**
	 * 批量锁定用户
	 */
	@Override
	public void lockBatch(List<String> userIds) {
		if (CollectionUtils.isNotEmpty(userIds)) {
			userIds.forEach(userId -> getUserInfoService().updateStatus(userId, UserStatusCode.BAN));
		}
	}

	/**
	 * 解锁用户
	 */
	@Override
	public void unlock(String userId) {
		getUserInfoService().updateStatus(userId, UserStatusCode.NORMAL);
	}

	/**
	 * 批量解锁用户
	 */
	@Override
	public void unlockBatch(List<String> userIds) {
		if (CollectionUtils.isNotEmpty(userIds)) {
			userIds.forEach(userId -> getUserInfoService().updateStatus(userId, UserStatusCode.NORMAL));
		}
	}

	/**
	 * 获取用户详情
	 */
	@Override
	public UserInfoWhole getUserInfoWhole(String userId) {
		// 用户信息
		UserInfoDto userInfo = getUserInfoService().findById(userId);
		UserExpandInfoDto userExpandInfo = getUserExpandInfoService().findById(userId);
		// 角色信息
		List<String> rolesIds = getUserRoleService().findRoleIdsByUserId(userId);
		List<RoleDto> rolesByUser = getRoleService().findByIds(rolesIds);
		// 部门组织
		List<String> userDeptIds = getUserDeptService().findDeptIdsByUser(userId);
		List<DeptDto> deptListByUser = getDeptService().findByIds(userDeptIds);
		return new UserInfoWhole().setUserInfo(userInfo).setUserExpandInfo(userExpandInfo).setRoles(rolesByUser)
				.setDeptList(deptListByUser);
	}

}
