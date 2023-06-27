package com.shaw.iam.param.user;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户修改手机号参数
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户修改手机号参数")
public class UserChangePhoneParam {

	@Schema(description = "新手机号")
	@NotBlank(message = "手机号不可为空")
	// @Pattern(regexp = "")
	private String phone;

	@Schema(description = "原手机号验证码")
	@NotBlank(message = "原手机号验证码不可为空")
	private String oldCaptcha;

	@Schema(description = "新手机号验证码")
	@NotBlank(message = "新手机号验证码不可为空")
	private String newCaptcha;

}
