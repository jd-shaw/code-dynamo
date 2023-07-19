package com.shaw.mysql.jpa.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageQuery extends Query {

	private int page = 1;
	private int limit = 20;

}
