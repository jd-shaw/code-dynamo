package com.shaw.iam.core.upms.service;

import java.util.List;

import com.shaw.iam.dto.scope.DataScopeDto;

/**
 * 用户数据权限关联关系
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserDataScopeService {

	boolean existsByDataScopeId(String dataScopeId);

	void deleteByDataScopeId(String dataScopeId);

	void saveAssign(String userId, String dataScopeId);

	void saveAssignBatch(List<String> userIds, String dataScopeId);

	String findDataScopeIdByUserId(String userId);
}
