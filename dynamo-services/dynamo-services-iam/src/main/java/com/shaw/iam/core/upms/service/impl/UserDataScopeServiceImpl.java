package com.shaw.iam.core.upms.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.rest.dto.BaseDto;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.core.dept.service.DeptService;
import com.shaw.iam.core.scope.service.DataScopeDeptService;
import com.shaw.iam.core.scope.service.DataScopeService;
import com.shaw.iam.core.scope.service.DataScopeUserService;
import com.shaw.iam.core.upms.dao.UserDataScopeDao;
import com.shaw.iam.core.upms.entity.UserDataScope;
import com.shaw.iam.core.upms.service.UserDataScopeService;
import com.shaw.iam.core.user.service.UserDeptService;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.dto.scope.DataScopeDto;
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
public class UserDataScopeServiceImpl implements UserDataScopeService {

	private final UserDataScopeDao userDataScopeDao;

	private final UserDeptService userDeptService;
	private final DataScopeUserService dataScopeUserService;
	private final DataScopeDeptService dataScopeDeptService;
	private final DeptService deptService;

	@Override
	public boolean existsByDataScopeId(String dataScopeId) {
		return getUserDataScopeDao().existsByDataScopeId(dataScopeId);
	}

	@Override
	public void deleteByDataScopeId(String dataScopeId) {
		getUserDataScopeDao().deleteByDataScopeId(dataScopeId);
	}

	/**
	 * 给用户数据权限关联关系
	 */
	@Override
	@Transactional
	@CacheEvict(value = { CachingCode.USER_DATA_SCOPE }, key = "#userId")
	public void saveAssign(String userId, String dataScopeId) {
		// 先删除用户拥有的数据权限
		getUserDataScopeDao().deleteByUserId(userId);
		if (Objects.nonNull(dataScopeId)) {
			UserDataScope userDataScope = new UserDataScope(userId, dataScopeId);
			userDataScope.setId(RandomUIDUtils.getUID());
			userDataScope.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
			getUserDataScopeDao().save(userDataScope);
		}
	}

	/**
	 * 给用户数据权限关联关系
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = { CachingCode.USER_DATA_SCOPE }, key = "#userIds")
	public void saveAssignBatch(List<String> userIds, String dataScopeId) {
		// 先删除用户拥有的数据权限
		getUserDataScopeDao().deleteByUserIdIn(userIds);

		List<UserDataScope> userDataScopes = userIds.stream().map(userId -> {
			UserDataScope userDataScope = new UserDataScope(userId, dataScopeId);
			userDataScope.setId(RandomUIDUtils.getUID());
			userDataScope.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
			return userDataScope;
		}).collect(Collectors.toList());
		getUserDataScopeDao().saveAll(userDataScopes);
	}

	/**
	 * 查询用户所对应的数据权限id
	 */
	@Override
	public String findDataScopeIdByUserId(String userId) {
		return getUserDataScopeDao().findByUserId(userId).map(UserDataScope::getDataScopeId).orElse(null);
	}

	/**
	 * 查找自己及子级部门
	 */
	private Set<String> findSubDeptList(String userId) {
		List<String> deptIds = getUserDeptService().findDeptIdsByUser(userId);
		Map<String, DeptDto> deptMap = getDeptService().findAll().stream()
				.collect(Collectors.toMap(BaseDto::getId, Function.identity()));
		Set<String> deptOrgCodes = deptIds.stream().map(deptMap::get).map(DeptDto::getOrgCode)
				.collect(Collectors.toSet());
		return deptMap.values().stream().filter(dept -> this.judgeSubDept(dept.getOrgCode(), deptOrgCodes))
				.map(BaseDto::getId).collect(Collectors.toSet());
	}

	/**
	 * 判断是否是子部门
	 */
	private boolean judgeSubDept(String orgCode, Set<String> orgCodes) {
		return orgCodes.stream().anyMatch(s -> StringUtils.startsWith(s, orgCode));
	}

}
