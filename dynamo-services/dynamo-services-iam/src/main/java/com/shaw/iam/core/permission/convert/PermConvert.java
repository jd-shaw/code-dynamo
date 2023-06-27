package com.shaw.iam.core.permission.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.permission.entity.PermMenu;
import com.shaw.iam.core.permission.entity.PermPath;
import com.shaw.iam.core.permission.entity.RequestPath;
import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.param.permission.PermMenuParam;
import com.shaw.iam.param.permission.PermPathParam;

/**
 * 权限转换
 *
 * @author shaw
 * @date 2023/06/20
 */
@Mapper
public interface PermConvert {

    PermConvert CONVERT = Mappers.getMapper(PermConvert.class);

    PermPathDto convert(PermPath in);

    PermPath convert(PermPathParam in);

    PermPath convert(PermPathDto in);

    PermPath convert(RequestPath in);

    PermMenu convert(PermMenuParam in);

    PermMenuDto convert(PermMenu in);

}
