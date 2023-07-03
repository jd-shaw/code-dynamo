package com.shaw.iam.core.role.service;

import java.util.List;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.dto.KeyValue;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.param.role.RoleParam;

/**
 * 角色
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface RoleService {

	RoleDto add(RoleParam roleParam);

	RoleDto update(RoleParam roleParam);

	void delete(String roleId);

	List<RoleDto> findAll();

	PageResult<RoleDto> page(PageParam pageParam, RoleParam roleParam);

	List<KeyValue> dropdown();

	RoleDto findById(String id);

	List<RoleDto> findByIds(List<String> ids);

	boolean existsByCode(String code);

	boolean existsByName(String name);

	boolean existsByCodeNotId(String code, String id);

	boolean existsByNameNotId(String name, String id);
}
