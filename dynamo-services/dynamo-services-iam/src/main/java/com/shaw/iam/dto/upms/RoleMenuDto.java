package com.shaw.iam.dto.upms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/6/27
 */
@Data
@Accessors(chain = true)
@Schema(title = "菜单及权限类")
public class RoleMenuDto {

	@Schema(description = "id")
	private String id;

	@Schema(description = "角色id")
	private String roleId;

	@Schema(description = "终端类型")
	private String clientCode;

	@Schema(description = "资源权菜单权限id限码集合")
	private String permissionId;

}
