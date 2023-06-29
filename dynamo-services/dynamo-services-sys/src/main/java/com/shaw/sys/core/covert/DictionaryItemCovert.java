package com.shaw.sys.core.covert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.sys.core.dto.DictionaryItemDto;
import com.shaw.sys.core.dto.DictionaryItemSimpleDto;
import com.shaw.sys.core.entity.DictionaryItem;
import com.shaw.sys.core.param.DictionaryItemParam;

/**
 * @author xjd
 * @date 2023/06/28
 */
@Mapper
public interface DictionaryItemCovert {

	DictionaryItemCovert CONVERT = Mappers.getMapper(DictionaryItemCovert.class);

	DictionaryItem convert(DictionaryItemParam in);

	DictionaryItemDto convert(DictionaryItem in);

	DictionaryItemSimpleDto convertSimple(DictionaryItem in);

}
