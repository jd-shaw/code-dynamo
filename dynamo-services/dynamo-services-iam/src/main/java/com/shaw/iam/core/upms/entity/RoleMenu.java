package com.shaw.iam.core.upms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.permission.convert.RoleMenuConvert;
import com.shaw.iam.dto.upms.RoleMenuDto;
import com.shaw.iam.param.upms.RoleMenuParam;
import com.shaw.mysql.jpa.entity.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 角色权限关联关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "iam_role_menu")
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BaseDomain implements EntityBaseFunction<RoleMenuDto> {

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

	public RoleMenu(String id, String roleId, String clientCode, String permissionId) {
		super(id, null);
		this.roleId = roleId;
		this.clientCode = clientCode;
		this.permissionId = permissionId;
	}

	public RoleMenu(String roleId, String clientCode, String permissionId) {
		this.roleId = roleId;
		this.clientCode = clientCode;
		this.permissionId = permissionId;
	}

	public static RoleMenu init(RoleMenuParam in) {
		return RoleMenuConvert.CONVERT.convert(in);
	}

	@Override
	public RoleMenuDto toDto() {
		return RoleMenuConvert.CONVERT.convert(this);
	}
}
