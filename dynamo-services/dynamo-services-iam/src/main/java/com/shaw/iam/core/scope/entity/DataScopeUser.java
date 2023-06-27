package com.shaw.iam.core.scope.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 数据范围用户关联配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "iam_data_scope_user")
public class DataScopeUser extends BaseDomain {

	/** 数据范围id */
	private String dataScopeId;

	/** 用户id */
	private String userId;

}
