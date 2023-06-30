package com.shaw.iam.core.dept.service;

import java.util.List;

import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.dto.dept.DeptTreeResult;
import com.shaw.iam.param.dept.DeptParam;

/**
 * @author shaw
 * @date 2023/06/20
 */
public interface DeptService {

	List<DeptDto> findByIds(List<String> ids);

	DeptDto add(DeptParam param);

	List<DeptTreeResult> tree();

	DeptDto findById(String id);

	DeptDto update(DeptParam param);

	void delete(String id);

	boolean deleteAndChildren(String id);
}
