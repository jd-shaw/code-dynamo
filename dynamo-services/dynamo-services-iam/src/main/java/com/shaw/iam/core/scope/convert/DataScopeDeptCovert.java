package com.shaw.iam.core.scope.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.iam.core.scope.entity.DataScopeDept;
import com.shaw.iam.dto.scope.DataScopeDeptDto;
import com.shaw.iam.param.scope.DataScopeDeptParam;

/**
 * @author shaw
 * @date 2023/6/28
 */
@Mapper
public interface DataScopeDeptCovert {

	DataScopeDeptCovert CONVERT = Mappers.getMapper(DataScopeDeptCovert.class);

	DataScopeDept convert(DataScopeDeptParam in);

	DataScopeDeptDto convert(DataScopeDept in);

}
