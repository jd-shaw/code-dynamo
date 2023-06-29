package com.shaw.sys.core.service;

import java.util.List;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.sys.core.dto.DictionaryDto;
import com.shaw.sys.core.param.DictionaryParam;

/**
 * @author shaw
 * @date 2020/4/10 15:52
 */
public interface DictionaryService {

	DictionaryDto add(DictionaryParam param);

	void delete(String id);

	DictionaryDto update(DictionaryParam param);

	boolean existsByCode(String code);

	boolean existsByCode(String code, String id);

	DictionaryDto findById(String id);

	List<DictionaryDto> findAll();

	PageResult<DictionaryDto> page(PageParam pageParam, DictionaryParam clientParam);
}
