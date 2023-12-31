package com.shaw.iam.dto.scope;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户数据范围权限详细信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户数据范围权限详细信息")
public class DataScopeUserInfoDto {

	@Schema(description = "权限关联id")
	private String id;

	@Schema(description = "用户id")
	private String userId;

	@Schema(description = "用户名称")
	private String name;

	@Schema(description = "用户账号")
	private String username;

}
