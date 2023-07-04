package com.shaw.iam.core.scope.service;

import java.util.List;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.dto.scope.DataScopeDto;
import com.shaw.iam.param.permission.PermPathParam;
import com.shaw.iam.param.scope.DataScopeDeptParam;
import com.shaw.iam.param.scope.DataScopeParam;

/**
 * 数据范围权限
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface DataScopeService {

	void add(DataScopeParam param);

	void update(DataScopeParam param);

	void delete(String id);

	void saveDeptAssign(DataScopeDeptParam param);

	List<String> findDeptIds(String id);

	boolean existsByCode(String code);

	boolean existsByCodeAndIdNot(String code, String id);

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, String id);

	DataScopeDto findById(String id);

	DataScopeDto findDataScopeByUserId(String userId);

	List<DataScopeDto> findAll();

	PageResult<DataScopeDto> page(PageParam pageParam, DataScopeParam param);

}
