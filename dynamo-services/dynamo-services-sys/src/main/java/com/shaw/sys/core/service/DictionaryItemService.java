package com.shaw.sys.core.service;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.sys.core.dto.DictionaryItemDto;
import com.shaw.sys.core.dto.DictionaryItemSimpleDto;
import com.shaw.sys.core.entity.DictionaryItem;
import com.shaw.sys.core.param.DictionaryItemParam;
import com.shaw.sys.core.param.DictionaryParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shaw
 * @date 2023/06/28
 */
public interface DictionaryItemService {

	@Transactional
	DictionaryItemDto add(DictionaryItemParam param);

	@Transactional(rollbackFor = Exception.class)
	DictionaryItemDto update(DictionaryItemParam param);

	void delete(String id);

	DictionaryItemDto findById(String id);

	List<DictionaryItem> findEnableByCode(String dictCode, String code);

	List<DictionaryItemDto> findByDictionaryId(String dictionaryId);

	List<DictionaryItemDto> findEnableByDictCode(String dictCode);

	PageResult<DictionaryItemDto> page(PageParam pageParam, DictionaryItemParam param);

	boolean existsByCode(String code, String dictId);

	boolean existsByCode(String code, String dictId, String id);

	List<DictionaryItemSimpleDto> findAll();

	List<DictionaryItemSimpleDto> findAllByEnable();
}
