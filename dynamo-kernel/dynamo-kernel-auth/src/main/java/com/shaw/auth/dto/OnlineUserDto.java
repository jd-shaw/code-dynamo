package com.shaw.auth.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "在线用户")
public class OnlineUserDto {

	@Schema(description = "用户id")
	private String userId;

	@Schema(description = "会话id")
	private String sessionId;

	@Schema(description = "用户名称")
	private String name;

	@Schema(description = "用户账号")
	private String userName;

	@Schema(description = "创建时间")
	private LocalDateTime creationTime;

	@Schema(description = "剩余有效时间(秒)")
	private Long timeout;

	@Schema(description = "Token列表")
	private List<TokenSign> tokenSigns;

	@Data
	@Accessors(chain = true)
	@Schema(title = "Token签名")
	public static class TokenSign {

		@Schema(description = "token值")
		private String value;

		@Schema(description = "所在设备标识")
		private String device;

	}

}
