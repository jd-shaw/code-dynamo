package com.shaw.iam.core.upms.service;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserRoleService {

	List<String> findRoleIdsByUserId(String userId);

	void saveAssign(String userId, List<String> roleIds);

	void saveAssignBatch(List<String> userIds, List<String> roleIds);

	boolean existsByRoleId(String roleId);
}
