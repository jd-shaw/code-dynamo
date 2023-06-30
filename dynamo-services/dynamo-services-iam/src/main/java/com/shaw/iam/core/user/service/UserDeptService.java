package com.shaw.iam.core.user.service;

import java.util.List;

/**
 * @author shaw
 * @date 2023/6/29
 */
public interface UserDeptService {
	void saveAssign(String userId, List<String> deptIds);

	void saveAssignBatch(List<String> userIds, List<String> deptIds);

	List<String> findDeptIdsByUser(String userId);
}
