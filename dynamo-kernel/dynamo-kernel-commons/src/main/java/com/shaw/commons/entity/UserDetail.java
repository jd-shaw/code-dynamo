package com.shaw.commons.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户信息类
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class UserDetail {

	/** 用户id */
	private String id;

	/** 用户名称 */
	private String name;

	/** 账号 */
	private String username;

	@JsonIgnore
	private transient String password;

	/** 拥有权限的应用列表 */
	private List<String> appIds;

	/** 是否管理员 */
	private boolean isAdmin;

	/**
	 * 账号状态
	 */
	private Integer status;

}
