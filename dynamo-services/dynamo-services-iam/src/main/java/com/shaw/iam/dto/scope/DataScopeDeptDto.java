package com.shaw.iam.dto.scope;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/6/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "部门、数据范围权限")
public class DataScopeDeptDto extends BaseDto {

	@Schema(description = "类型")
	private String dataScopeId;

	@Schema(description = "部门id")
	private String deptId;

}
