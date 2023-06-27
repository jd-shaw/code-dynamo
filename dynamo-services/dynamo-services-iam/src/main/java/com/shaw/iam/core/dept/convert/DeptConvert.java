package com.shaw.iam.core.dept.convert;

import com.shaw.iam.param.dept.DeptParam;
import com.shaw.iam.core.dept.entity.Dept;
import com.shaw.iam.dto.dept.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 部门机构转换
 *
 * @author shaw
 * @date 2023/06/20
 */
@Mapper
public interface DeptConvert {

    DeptConvert CONVERT = Mappers.getMapper(DeptConvert.class);

    Dept convert(DeptDto in);

    Dept convert(DeptParam in);

    DeptDto convert(Dept in);

}
