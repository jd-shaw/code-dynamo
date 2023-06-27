package com.shaw.iam.core.upms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 角色权限关联关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "iam_role_menu")
@NoArgsConstructor
public class RoleMenu extends BaseDomain {

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 终端类型
	 */
	private String clientCode;

	/**
	 * 菜单权限id
	 */
	private String permissionId;

	public RoleMenu(String roleId, String clientCode, String permissionId) {
		this.roleId = roleId;
		this.clientCode = clientCode;
		this.permissionId = permissionId;
	}

}
