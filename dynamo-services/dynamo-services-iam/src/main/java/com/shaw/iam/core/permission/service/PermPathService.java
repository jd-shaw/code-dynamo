package com.shaw.iam.core.permission.service;

import java.util.List;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.param.permission.PermPathBatchEnableParam;
import com.shaw.iam.param.permission.PermPathParam;

/**
 * 请求权限
 *
 * @author shaw
 * @date 2023/06/20
 */

public interface PermPathService {

	void save(PermPathParam param);

	void update(PermPathParam param);

	void batchUpdateEnable(PermPathBatchEnableParam param);

	void delete(String id);

	void delete(List<String> ids);

	PermPathDto findById(String id);

	List<PermPathDto> findByIds(List<String> ids);

	List<PermPathDto> findAll();

	PageResult<PermPathDto> page(PageParam pageParam, PermPathParam param);
}
