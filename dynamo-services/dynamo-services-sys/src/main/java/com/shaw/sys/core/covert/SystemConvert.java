package com.shaw.sys.core.covert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.sys.core.dto.SystemParameterDto;
import com.shaw.sys.core.entity.SystemParameter;
import com.shaw.sys.core.param.SystemParameterParam;

/**
 * 系统参数和系统配置实体类转换
 *
 * @author shaw
 * @date 2023/06/28
 */
@Mapper
public interface SystemConvert {

	SystemConvert CONVERT = Mappers.getMapper(SystemConvert.class);

	SystemParameterDto convert(SystemParameter in);

	SystemParameter convert(SystemParameterParam in);

}
