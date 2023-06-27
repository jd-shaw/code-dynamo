package com.shaw.iam.core.role.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.role.entity.Role;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.param.role.RoleParam;

@Mapper
public interface RoleConvert {

	RoleConvert CONVERT = Mappers.getMapper(RoleConvert.class);

	RoleDto convert(Role in);

	Role convert(RoleParam in);

}
