package com.shaw.iam.core.client.convert;

import com.shaw.iam.param.client.LoginTypeParam;
import com.shaw.iam.core.client.entity.LonginType;
import com.shaw.iam.dto.client.LoginTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 认证终端
 *
 * @author bootx
 * @date 2023/06/20
 */
@Mapper
public interface LoginTypeConvert {

    LoginTypeConvert CONVERT = Mappers.getMapper(LoginTypeConvert.class);

    LonginType convert(LoginTypeParam in);

    LoginTypeDto convert(LonginType in);

}
