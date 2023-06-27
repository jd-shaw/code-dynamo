package com.shaw.iam.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.shaw.commons.entity.UserDetail;
import com.shaw.commons.rest.dto.BaseDto;
import com.shaw.iam.code.UserStatusCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "用户信息")
public class UserInfoDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 5881350477107722635L;

	@Schema(description = "用户id")
	private String id;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "账号")
	private String username;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "手机号")
	private String phone;

	@Schema(description = "邮箱")
	private String email;

	@Schema(description = "终端id列表")
	private List<String> clientIdList = new ArrayList<>();

	@Schema(description = "注册来源")
	private String source;

	@Schema(description = "是否管理员")
	private boolean admin;

	/**
	 * @see UserStatusCode
	 */
	@Schema(description = "账号状态")
	private Integer status;

	@Schema(description = "注册时间")
	private LocalDateTime registerTime;

	public UserDetail toUserDetail() {
		List<String> clientIds = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(this.getClientIdList())) {
			clientIds = this.getClientIdList().stream().map(String::valueOf).collect(Collectors.toList());
		}
		return new UserDetail().setId(this.getId()).setPassword(this.password).setUsername(this.getUsername())
				.setName(this.name).setAdmin(this.admin).setAppIds(clientIds).setStatus(this.status);
	}

}
