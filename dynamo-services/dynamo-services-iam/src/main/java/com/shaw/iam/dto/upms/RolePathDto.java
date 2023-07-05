package com.shaw.iam.dto.upms;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xjd
 * @date 2023/7/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "角色请求权限消息关系")
public class RolePathDto extends BaseDto {

	@Schema(description = "角色id")
	private String roleId;

	@Schema(description = "权限id")
	private String permissionId;

}
