package com.shaw.iam.core.upms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户角色关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "iam_user_role")
@NoArgsConstructor
public class UserRole extends BaseDomain {

	/** 用户 */
	private String userId;

	/** 角色 */
	private String roleId;

}
