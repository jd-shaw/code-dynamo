package com.shaw.iam.core.scope.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.scope.convert.DataScopeDeptCovert;
import com.shaw.iam.dto.scope.DataScopeDeptDto;
import com.shaw.mysql.jpa.entity.BaseDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 数据范围部门关联配置
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
@Table(name = "iam_data_scope_dept")
public class DataScopeDept extends BaseDomain  implements EntityBaseFunction<DataScopeDeptDto> {

	/** 数据范围id */
	private String dataScopeId;

	/** 部门id */
	private String deptId;

	@Override
	public DataScopeDeptDto toDto() {
		return DataScopeDeptCovert.CONVERT.convert(this);
	}
}
