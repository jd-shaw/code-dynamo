package com.shaw.message.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 消息用户关联信息
 *
 * @author shaw
 * @date 2023/06/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "sys_site_message_user")
public class SiteMessageUser extends BaseDomain {

	/** 消息id */
	private String messageId;

	/** 接收者id */
	private String receiveId;

	/** 已读/未读 */
	private boolean haveRead;

	/** 已读时间 */
	private LocalDateTime readTime;

}