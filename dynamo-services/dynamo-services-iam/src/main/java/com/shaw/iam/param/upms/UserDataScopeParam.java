package com.shaw.iam.param.upms;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Schema(title = "用户数据权限参数")
public class UserDataScopeParam {

	@Schema(description = "用户的ID", required = true)
	@NotNull(message = "用户 ID 不能为空")
	private String userId;

	@Schema(description = "数据权限的ID集合", required = true)
	private String dataScopeId;

}
