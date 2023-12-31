package com.shaw.iam.param.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 认证终端
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "登录方式")
public class LoginTypeParam {

	@Schema(description = "主键")
	private String id;

	@Schema(description = "编码")
	private String code;

	@Schema(description = "名称")
	private String name;

	/**
	 * password 密码登录, openId 第三方登录
	 */
	@Schema(description = "类型")
	private String type;

	@Schema(description = "是否可用")
	private int enable;

	@Schema(description = "是否启用验证码")
	private int captcha;

	@Schema(description = "在线时长 分钟")
	private Long timeout;

	@Schema(description = "密码错误几次冻结 -1表示不限制")
	private Integer pwdErrNum;

	@Schema(description = "描述")
	private String description;

}
