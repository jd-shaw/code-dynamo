package com.shaw.auth.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "登录方式")
public class AuthLoginType {

	/** 登录方式id */
	private String id;

	/** 编码 */
	private String code;

	/** 名称 */
	private String name;

	/** 在线时长 分钟 */
	private long timeout;

	/** 是否可用 */
	private int enable;

	/** 密码错误几次冻结 */
	private int pwdErrNum;

	/** 是否需要验证码 */
	private boolean captcha;

}
