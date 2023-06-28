package com.shaw.iam.core.permission.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.upms.entity.RoleMenu;
import com.shaw.iam.dto.upms.RoleMenuDto;

/**
 * @author xjd
 * @date 2023/6/27
 */
@Mapper
public interface RoleMenuConvert {

	RoleMenuConvert CONVERT = Mappers.getMapper(RoleMenuConvert.class);

	RoleMenu convert(RoleMenuDto in);

	RoleMenuDto convert(RoleMenu in);

	List<RoleMenuDto> convert(List<RoleMenu> in);

}
