package com.shaw.message.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.message.convert.SiteMessageConvert;
import com.shaw.message.dto.SiteMessageDto;
import com.shaw.mysql.jpa.po.BaseDomain;

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
@Entity
@Accessors(chain = true)
@Table(name = "site_message")
public class SiteMessage extends BaseDomain implements EntityBaseFunction<SiteMessageDto> {

	/** 消息标题 */
	private String title;

	/** 消息内容 */
	private String content;

	/**
	 * 接收对象类型 全体/指定用户
	 */
	private String receiveType;

	/**
	 * 发布状态
	 */
	private String sendState;

	/** 发送者id */
	private Long senderId;

	/** 发送者姓名 */
	private String senderName;

	/** 发送时间 */
	private LocalDateTime senderTime;

	/** 撤销时间 */
	private LocalDateTime cancelTime;

	/** 截至有效期 有效超过有效期后全体通知将无法看到 */
	private LocalDate efficientTime;

	@Override
	public SiteMessageDto toDto() {
		return SiteMessageConvert.CONVERT.convert(this);
	}

}
