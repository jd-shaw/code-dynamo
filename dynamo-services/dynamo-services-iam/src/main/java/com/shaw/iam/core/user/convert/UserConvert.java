package com.shaw.iam.core.user.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.user.entity.UserExpandInfo;
import com.shaw.iam.core.user.entity.UserInfo;
import com.shaw.iam.dto.user.UserExpandInfoDto;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.param.user.UserInfoParam;

@Mapper
public interface UserConvert {

	UserConvert CONVERT = Mappers.getMapper(UserConvert.class);

	UserInfo convert(UserInfoParam in);

	UserInfoDto convert(UserInfo in);

	UserExpandInfoDto convert(UserExpandInfo in);

}
