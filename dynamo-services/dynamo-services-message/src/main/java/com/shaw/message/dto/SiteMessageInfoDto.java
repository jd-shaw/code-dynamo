package com.shaw.message.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "站内信信息详情")
public class SiteMessageInfoDto {

	@Schema(description = "消息id")
	private String id;

	@Schema(description = "标题")
	private String title;

	@Schema(description = "发送人id")
	private String senderId;

	@Schema(description = "发送人姓名")
	private String senderName;

	@Schema(description = "接收类型")
	private String receiveType;

	@Schema(description = "发送时间")
	private LocalDateTime senderTime;

	@Schema(description = "发送状态")
	private String sendState;

	@Schema(description = "撤回时间")
	private LocalDateTime cancelTime;

	@Schema(description = "接收人id")
	private Long receiveId;

	@Schema(description = "是否已读")
	private Boolean haveRead;

	@Schema(description = "读取时间")
	private LocalDateTime readTime;

	@Schema(description = "截至有效期")
	private LocalDate efficientTime;

}
