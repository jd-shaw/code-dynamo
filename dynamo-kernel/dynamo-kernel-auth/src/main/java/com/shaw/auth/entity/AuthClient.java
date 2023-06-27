package com.shaw.auth.entity;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
public class AuthClient {

	/** 终端id */
	private String id;

	/** 编码 */
	private String code;

	/** 名称 */
	private String name;

	/** 在线时长 分钟 */
	private long timeout;

	/** 是否可用 */
	private boolean enable;

	/** 关联登录方式Id */
	private List<String> loginTypeIds;

}
