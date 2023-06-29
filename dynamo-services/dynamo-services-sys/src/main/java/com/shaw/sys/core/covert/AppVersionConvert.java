package com.shaw.sys.core.covert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.sys.core.dto.AppVersionDto;
import com.shaw.sys.core.entity.AppVersion;
import com.shaw.sys.core.param.AppVersionParam;

/**
 * 转换
 *
 * @author shaw
 * @date 2023/06/28
 */
@Mapper
public interface AppVersionConvert {

	AppVersionConvert CONVERT = Mappers.getMapper(AppVersionConvert.class);

	AppVersion convert(AppVersionParam in);

	AppVersion convert(AppVersionDto in);

	AppVersionDto convert(AppVersion in);

}
