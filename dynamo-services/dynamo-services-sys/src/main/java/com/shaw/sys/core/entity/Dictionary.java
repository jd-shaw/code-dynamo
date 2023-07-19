package com.shaw.sys.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.sys.core.covert.DictionaryConvert;
import com.shaw.sys.core.dto.DictionaryDto;
import com.shaw.sys.core.param.DictionaryParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典
 *
 * @author shaw
 * @date 2023/06/28
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_dict")
public class Dictionary extends BaseDomain implements EntityBaseFunction<DictionaryDto> {

	/** 名称 */
	private String name;

	/** 分类标签 */
	private String groupTag;

	/** 编码 */
	private String code;

	/** 备注 */
	private String remark;

	/** 是否启用 */
	private Boolean enable;

	public static Dictionary init(DictionaryParam in) {
		return DictionaryConvert.CONVERT.convert(in);
	}

	@Override
	public DictionaryDto toDto() {
		return DictionaryConvert.CONVERT.convert(this);
	}

}
