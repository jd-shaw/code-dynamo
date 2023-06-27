package com.shaw.iam.core.role.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.role.convert.RoleConvert;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.param.role.RoleParam;
import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "iam_role")
@Accessors(chain = true)
public class Role extends BaseDomain implements EntityBaseFunction<RoleDto> {

	/** 编码 */
	private String code;

	/** 名称 */
	private String name;

	/** 是否系统内置 不能修改 */
	private boolean internal;

	/** 描述 */
	private String remark;

	public static Role init(RoleParam in) {
		return RoleConvert.CONVERT.convert(in);
	}

	@Override
	public RoleDto toDto() {
		return RoleConvert.CONVERT.convert(this);
	}

}
