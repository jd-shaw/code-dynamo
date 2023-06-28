package com.shaw.iam.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户登录后所需的基础信息信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户登录后所需的基础信息信息")
public class LoginAfterUserInfo {

	@Schema(description = "用户id")
	private String userId;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "账号")
	private String username;

	@Schema(description = "头像")
	private String avatar;

}
