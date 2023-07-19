package com.shaw.sys.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.sys.core.covert.DictionaryItemCovert;
import com.shaw.sys.core.dto.DictionaryItemDto;
import com.shaw.sys.core.dto.DictionaryItemSimpleDto;
import com.shaw.sys.core.param.DictionaryItemParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 字典项
 *
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "sys_dict_item")
public class DictionaryItem extends BaseDomain implements EntityBaseFunction<DictionaryItemDto> {

	/** 字典ID */
	private String dictId;

	/** 字典编码 */
	private String dictCode;

	/** 字典项编码 */
	private String code;

	/** 名称 */
	private String name;

	/** 字典项排序 */
	private Double sortNo;

	/** 是否启用 */
	private Boolean enable;

	/** 备注 */
	private String remark;

	public static DictionaryItem init(DictionaryItemParam in) {
		return DictionaryItemCovert.CONVERT.convert(in);
	}

	@Override
	public DictionaryItemDto toDto() {
		return DictionaryItemCovert.CONVERT.convert(this);
	}

	/**
	 * 转换成简单对象
	 */
	public DictionaryItemSimpleDto toSimpleDto() {
		return DictionaryItemCovert.CONVERT.convertSimple(this);

	}

}
