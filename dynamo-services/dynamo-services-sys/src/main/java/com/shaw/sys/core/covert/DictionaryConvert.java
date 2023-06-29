package com.shaw.sys.core.covert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.sys.core.dto.DictionaryDto;
import com.shaw.sys.core.entity.Dictionary;
import com.shaw.sys.core.param.DictionaryParam;

/**
 * 渠道转换
 *
 * @author shaw
 * @date 2023/06/28
 */
@Mapper
public interface DictionaryConvert {

	DictionaryConvert CONVERT = Mappers.getMapper(DictionaryConvert.class);

	Dictionary convert(DictionaryParam in);

	DictionaryDto convert(Dictionary in);

}
