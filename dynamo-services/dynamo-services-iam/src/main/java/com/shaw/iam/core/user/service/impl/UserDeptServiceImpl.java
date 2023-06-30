package com.shaw.iam.core.user.service.impl;

import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.core.dept.event.DeptDeleteEvent;
import com.shaw.iam.core.dept.service.DeptService;
import com.shaw.iam.core.user.dao.UserDeptDao;
import com.shaw.iam.core.user.entity.UserDept;
import com.shaw.iam.core.user.service.UserDeptService;
import com.shaw.iam.dto.dept.DeptDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shaw
 * @date 2023/6/29
 */
@Getter
@Service
@RequiredArgsConstructor
public class UserDeptServiceImpl implements UserDeptService {

	private final UserDeptDao userDeptDao;

	private final DeptService deptService;

	/**
	 * 给用户分配部门
	 */
	@Override
	@Transactional
	public void saveAssign(String userId, List<String> deptIds) {
		// 先删除用户拥有的部门
		getUserDeptDao().deleteByUserId(userId);
		// 然后给用户添加部门
		List<UserDept> userDeptList = this.createUserDepots(userId, deptIds);
		getUserDeptDao().saveAll(userDeptList);
	}

	/**
	 * 给用户分配部门 批量
	 */
	@Override
	@Transactional
	public void saveAssignBatch(List<String> userIds, List<String> deptIds) {
		// 先删除用户拥有的部门
		getUserDeptDao().deleteByUserIdIn(userIds);
		// 然后给用户添加部门
		List<UserDept> userDeptList = userIds.stream().map(userId -> this.createUserDepots(userId, deptIds))
				.flatMap(Collection::stream).collect(Collectors.toList());
		getUserDeptDao().saveAll(userDeptList);
	}

	/**
	 * 根据用户Id查询部门id
	 */
	@Override
	public List<String> findDeptIdsByUser(String userId) {
		return getUserDeptDao().findByUserId(userId).stream().map(UserDept::getDeptId).distinct()
				.collect(Collectors.toList());
	}

	/**
	 * 查询用户所对应的部门
	 */
	public List<DeptDto> findDeptListByUser(String userId) {
		return ResultConvertUtil.dtoListConvert(getDeptService().findByIds(this.findDeptIdsByUser(userId)));
	}

	/**
	 * 处理部门被删除的情况
	 */
	@EventListener
	public void DeptDeleteEventListener(DeptDeleteEvent event) {
		userDeptManager.deleteByDeptIds(event.getDeptIds());
	}

	/**
	 * 创建用户部门关联
	 */
	private List<UserDept> createUserDepots(String userId, List<String> deptIds) {
		return deptIds.stream().map(deptId -> new UserDept(userId, deptId)).collect(Collectors.toList());
	}

}
