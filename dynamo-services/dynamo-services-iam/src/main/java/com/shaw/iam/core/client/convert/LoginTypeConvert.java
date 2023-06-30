package com.shaw.iam.core.client.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.client.entity.LoginType;
import com.shaw.iam.dto.client.LoginTypeDto;
import com.shaw.iam.param.client.LoginTypeParam;

/**
 * 认证终端
 *
 * @author bootx
 * @date 2023/06/20
 */
@Mapper
public interface LoginTypeConvert {

	LoginTypeConvert CONVERT = Mappers.getMapper(LoginTypeConvert.class);

	LoginType convert(LoginTypeParam in);

	LoginTypeDto convert(LoginType in);

}
