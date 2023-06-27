package com.shaw.iam.core.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.user.convert.UserConvert;
import com.shaw.iam.dto.user.UserExpandInfoDto;
import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户扩展信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "iam_user_expand_info")
public class UserExpandInfo extends BaseDomain implements EntityBaseFunction<UserExpandInfoDto> {

	/** 性别 */
	private Integer sex;

	/** 头像 */
	private String avatar;

	/** 生日 */
	private LocalDate birthday;

	/** 上次登录时间 */
	private LocalDateTime lastLoginTime;

	/** 本次登录时间 */
	private LocalDateTime currentLoginTime;

	/** 是否初始密码 */
	private boolean initialPassword;

	/** 上次修改密码时间 */
	private LocalDateTime lastChangePasswordTime;

	@Override
	public UserExpandInfoDto toDto() {
		return UserConvert.CONVERT.convert(this);
	}

}
