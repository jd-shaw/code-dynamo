package com.shaw.sys.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.sys.core.covert.SystemConvert;
import com.shaw.sys.core.dto.SystemParameterDto;
import com.shaw.sys.core.param.SystemParameterParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统参数
 *
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "sys_param")
public class SystemParameter extends BaseDomain implements EntityBaseFunction<SystemParameterDto> {

	/** 参数名称 */
	private String name;

	/** 参数键名 */
	private String paramKey;

	/** 参数值 */
	private String value;

	/** 参数类型 */
	private Integer type;

	/** 是否启用 */
	private Boolean enable;

	/** 内置参数 */
	private boolean internal;

	/** 备注 */
	private String remark;

	@Override
	public SystemParameterDto toDto() {
		return SystemConvert.CONVERT.convert(this);
	}

	public static SystemParameter init(SystemParameterParam in) {
		return SystemConvert.CONVERT.convert(in);
	}

}
