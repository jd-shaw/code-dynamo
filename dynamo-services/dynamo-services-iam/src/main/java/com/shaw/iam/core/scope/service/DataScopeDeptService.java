package com.shaw.iam.core.scope.service;

import java.util.List;

import com.shaw.iam.core.scope.entity.DataScopeDept;
import com.shaw.iam.dto.scope.DataScopeDeptDto;

/**
 * @author shaw
 * @date 2023/6/28
 */
public interface DataScopeDeptService {

	List<DataScopeDeptDto> findByDateScopeId(String dataScopeId);

	void deleteByIds(List<String> ids);

	void deleteByDeptIds(List<String> deptIds);

	void save(List<DataScopeDept> dataScopeDepths);
}
