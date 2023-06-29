package com.shaw.sys.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shaw.commons.rest.dto.KeyValue;
import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * kv存储
 *
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "sys_key_value")
public class SysKeyValue extends BaseDomain {

	/**
	 * key值
	 */
	private String key;

	/**
	 * value值
	 */
	private String value;

	/**
	 * 转换成系统的 KayValue 对象
	 */
	@Transient
	public KeyValue toKeyValue() {
		return new KeyValue(key, value);
	}

	/**
	 * 从 KayValue 转换
	 */
	public static SysKeyValue init(KeyValue keyValue) {
		return new SysKeyValue(keyValue.getKey(), keyValue.getValue());
	}

}
