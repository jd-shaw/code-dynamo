package com.shaw.iam.core.scope.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.scope.convert.DataScopeConvert;
import com.shaw.iam.dto.scope.DataScopeDto;
import com.shaw.iam.param.scope.DataScopeParam;
import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据范围配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "iam_data_scope")
public class DataScope extends BaseDomain implements EntityBaseFunction<DataScopeDto> {

	/** 编码 */
	private String code;

	/** 名称 */
	private String name;

	/**
	 * 类型
	 */
	private Integer type;

	/** 备注 */
	private String remark;

	public static DataScope init(DataScopeParam in) {
		return DataScopeConvert.CONVERT.convert(in);
	}

	@Override
	public DataScopeDto toDto() {
		return DataScopeConvert.CONVERT.convert(this);
	}

}
