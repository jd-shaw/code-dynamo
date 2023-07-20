package com.shaw.sys.core.service;

import com.shaw.commons.function.SystemParamService;
import com.shaw.commons.rest.PageResult;
import com.shaw.mysql.jpa.po.PageQuery;
import com.shaw.sys.core.dto.SystemParameterDto;
import com.shaw.sys.core.param.SystemParameterParam;
import com.shaw.sys.core.query.SystemParameterQuery;

/**
 * 系统参数
 *
 * @author shaw
 * @date 2023/06/28
 */
public interface SystemParameterService extends SystemParamService {

	void add(SystemParameterParam param);

	void update(SystemParameterParam param);

	PageResult<SystemParameterDto> page(PageQuery query);

	SystemParameterDto findById(String id);

	String findByParamKey(String key);

	void delete(String id);

	boolean existsByKey(String key);

	boolean existsByKey(String key, String id);
}
