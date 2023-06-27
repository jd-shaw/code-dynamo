package com.shaw.iam.core.upms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 角色路径权限表
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "iam_role_path")
@NoArgsConstructor
public class RolePath extends BaseDomain {

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 权限id
	 */
	private String permissionId;

	public RolePath(String roleId, String permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

}
