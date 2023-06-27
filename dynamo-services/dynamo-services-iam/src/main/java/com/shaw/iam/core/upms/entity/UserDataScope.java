package com.shaw.iam.core.upms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户数据范围关联关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "iam_user_data_scope")
public class UserDataScope extends BaseDomain {

	/** 用户id */
	private String userId;

	/** 数据权限id */
	private String dataScopeId;

}
