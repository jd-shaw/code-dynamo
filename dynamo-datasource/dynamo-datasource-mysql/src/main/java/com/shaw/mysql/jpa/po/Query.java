package com.shaw.mysql.jpa.po;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shaw
 * @date 2023/7/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Query extends BaseParam {

	private String sort;
	private String direction = "DESC";

	public boolean hasSort() {
		return StringUtils.isNotEmpty(sort);
	}
}
