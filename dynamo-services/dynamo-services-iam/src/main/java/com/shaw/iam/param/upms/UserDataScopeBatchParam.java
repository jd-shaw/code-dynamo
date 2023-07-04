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
@Schema(title = "用户数据权限批量设置参数")
public class UserDataScopeBatchParam {

	@Schema(description = "用户的ID", required = true)
	@NotEmpty(message = "用户集合不能为空")
	private List<String> userIds;

	@Schema(description = "数据权限的ID集合", required = true)
	private String dataScopeId;

}
