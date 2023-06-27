package com.shaw.iam.param.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户修改邮箱参数
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户修改邮箱参数")
public class UserChangeEmailParam {

	@Schema(description = "新邮箱")
	@NotBlank(message = "邮箱不可为空")
	@Email(message = "邮箱格式不对")
	private String email;

	@Schema(description = "原邮箱验证码")
	@NotBlank(message = "原邮箱验证码不可为空")
	private String oldCaptcha;

	@Schema(description = "新邮箱验证码")
	@NotBlank(message = "新邮箱验证码不可为空")
	private String newCaptcha;

}
