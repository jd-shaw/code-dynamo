package com.shaw.iam.core.client.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.client.convert.LoginTypeConvert;
import com.shaw.iam.dto.client.LoginTypeDto;
import com.shaw.iam.param.client.LoginTypeParam;
import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 登录方式
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "iam_login_type")
@Accessors(chain = true)
public class LonginType extends BaseDomain implements EntityBaseFunction<LoginTypeDto> {

	/** 编码 */
	private String code;

	/** 名称 */
	private String name;

	/**
	 * password 密码登录, openId 第三方登录
	 */
	private String type;

	/** 在线时长 分钟 */
	private Long timeout;

	/** 是否需要验证码 */
	private boolean captcha;

	/** 是否系统内置 */
	@Column(name = "`system`")
	private boolean system;

	/** 密码错误几次冻结 -1表示不限制 */
	private Integer pwdErrNum;

	/** 是否可用 */
	private boolean enable;

	/** 描述 */
	private String description;

	public static LonginType init(LoginTypeParam in) {
		return LoginTypeConvert.CONVERT.convert(in);
	}

	@Override
	public LoginTypeDto toDto() {
		return LoginTypeConvert.CONVERT.convert(this);
	}

}
