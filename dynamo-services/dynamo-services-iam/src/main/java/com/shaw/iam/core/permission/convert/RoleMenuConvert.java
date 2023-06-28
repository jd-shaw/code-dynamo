package com.shaw.iam.core.permission.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.upms.entity.RoleMenu;
import com.shaw.iam.dto.upms.RoleMenuDto;
import com.shaw.iam.param.upms.RoleMenuParam;

/**
 * @author xjd
 * @date 2023/6/27
 */
@Mapper
public interface RoleMenuConvert {

	RoleMenuConvert CONVERT = Mappers.getMapper(RoleMenuConvert.class);

	RoleMenu convert(RoleMenuDto in);

	RoleMenu convert(RoleMenuParam in);

	RoleMenuDto convert(RoleMenu in);

}
