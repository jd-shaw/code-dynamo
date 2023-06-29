package com.shaw.iam.param.upms;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/6/28
 */
@Data
@Accessors(chain = true)
@Schema(title = "角色菜单")
public class RoleMenuParam implements Serializable {

	private static final long serialVersionUID = 4344723093613370279L;

	@Schema(description = "id")
	private String id;

	@Schema(description = "角色id")
	private String roleId;

	@Schema(description = "终端类型")
	private String clientCode;

	@Schema(description = "资源权菜单权限id限码集合")
	private String permissionId;

	public RoleMenuParam() {
	}

	public RoleMenuParam(String id, String roleId, String clientCode, String permissionId) {
	}

}
