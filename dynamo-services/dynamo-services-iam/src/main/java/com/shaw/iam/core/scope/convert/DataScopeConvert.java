package com.shaw.iam.core.scope.convert;

import com.shaw.iam.param.scope.DataScopeParam;
import com.shaw.iam.core.scope.entity.DataScope;
import com.shaw.iam.dto.scope.DataScopeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 数据范围转换类
 *
 * @author shaw
 * @date 2023/06/20
 */
@Mapper
public interface DataScopeConvert {

    DataScopeConvert CONVERT = Mappers.getMapper(DataScopeConvert.class);

    DataScope convert(DataScopeParam in);

    DataScopeDto convert(DataScope in);

}
