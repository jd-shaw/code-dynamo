package com.shaw.iam.core.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.upms.entity.RolePath;
import com.shaw.iam.dto.upms.RolePathDto;

@Mapper
public interface RolePathConvert {

	RolePathConvert CONVERT = Mappers.getMapper(RolePathConvert.class);

	RolePathDto convert(RolePath in);

}
