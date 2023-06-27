package com.shaw.iam.param.user;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户绑定第三方开放平台参数
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户绑定第三方开放平台参数")
public class UserBindThirdParam {

	@NotBlank(message = "授权码不可为空")
	@Schema(description = "授权码")
	private String authCode;

	@NotBlank(message = "第三方开放平台登录不可为空")
	@Schema(description = "第三方开放平台登录类型")
	private String loginType;

	// @NotBlank(message = "state不可为空")
	@Schema(description = "state")
	private String state;

}
