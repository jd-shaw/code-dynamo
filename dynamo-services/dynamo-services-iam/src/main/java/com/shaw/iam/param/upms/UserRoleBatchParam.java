package com.shaw.iam.param.upms;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Schema(title = "用户角色参数")
public class UserRoleBatchParam {

	@Schema(description = "用户的ID", required = true)
	@NotEmpty(message = "用户集合不能为空")
	private List<String> userIds;

	@Schema(description = "角色的ID集合", required = true)
	private List<String> roleIds;

}
