package com.shaw.iam.core.user.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections4.CollectionUtils;

import com.shaw.commons.entity.UserDetail;
import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.code.UserStatusCode;
import com.shaw.iam.core.user.convert.UserConvert;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.param.user.UserInfoParam;
import com.shaw.mysql.jpa.po.BaseDomain;
import com.shaw.utils.text.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户的核心信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "iam_user_info")
public class UserInfo extends BaseDomain implements EntityBaseFunction<UserInfoDto> {

	/** 名称 */
	private String name;

	/** 账号 */
	private String username;

	/** 密码 */
	private String password;

	/** 手机号 */
	private String phone;

	/** 邮箱 */
	private String email;

	/** 关联终端id集合 */
	private String clientIds;

	/** 注册来源 */
	private String source;

	/** 是否管理员 */
	private boolean isAdmin;

	/**
	 * 账号状态
	 * @see UserStatusCode
	 */
	private Integer status;

	/** 注册时间 */
	private LocalDateTime registerTime;

	@Override
	public UserInfoDto toDto() {
		UserInfoDto userInfoDto = UserConvert.CONVERT.convert(this);
		if (StringUtils.isNotBlank(this.getClientIds())) {
			List<String> collect = Arrays.stream(this.getClientIds().split(",")).collect(Collectors.toList());
			userInfoDto.setClientIdList(collect);
		}
		return userInfoDto;
	}

	public static UserInfo init(UserInfoParam param) {
		UserInfo userInfo = UserConvert.CONVERT.convert(param);
		if (CollectionUtils.isNotEmpty(param.getClientIdList())) {
			String appIds = String.join(",", param.getClientIdList());
			userInfo.setClientIds(appIds);
		}
		return userInfo;
	}

	public UserDetail toUserDetail() {
		return new UserDetail().setId(this.getId()).setPassword(this.password).setUsername(this.getUsername())
				.setName(this.name).setAdmin(this.isAdmin).setStatus(this.status);
	}

}
