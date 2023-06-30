package com.shaw.message.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 站内信
 *
 * @author shaw
 * @date 2023/06/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "站内信")
public class SiteMessageDto extends BaseDto {

	@Schema(description = "消息标题")
	private String title;

	@Schema(description = "消息内容")
	private String content;

	/**
	 */
	@Schema(description = "接收对象类型 全体/指定用户")
	private String receiveType;

	/**
	 */
	@Schema(description = "发布状态")
	private String sendState;

	@Schema(description = "发送者id")
	private Long senderId;

	@Schema(description = "发送者姓名")
	private String senderName;

	@Schema(description = "发送时间")
	private LocalDateTime senderTime;

	@Schema(description = "撤销时间")
	private LocalDateTime cancelTime;

	@Schema(description = "截至有效期 有效超过有效期后全体通知将无法看到")
	private LocalDate efficientTime;

}
