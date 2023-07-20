package com.shaw.sys.core.query;

import com.shaw.mysql.jpa.annotation.QueryField;
import com.shaw.mysql.jpa.commons.QueryType;
import com.shaw.mysql.jpa.po.PageQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/7/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "系统参数")
@EqualsAndHashCode(callSuper = true)
public class SystemParameterQuery extends PageQuery {

	@Schema(description = "主键")
	@QueryField(name = "paramName", type = QueryType.EQUAL)
	private String id;

	@Schema(description = "参数名称")
	@QueryField(name = "name", type = QueryType.LIKE)
	private String name;

	@Schema(description = "参数键名")
	@QueryField(name = "paramKey", type = QueryType.LIKE)
	private String paramKey;

	@QueryField(name = "value", type = QueryType.EQUAL)
	@Schema(description = "参数值")
	private String value;

	@QueryField(name = "enable", type = QueryType.EQUAL)
	@Schema(description = "启用状态")
	private Boolean enable;

	@QueryField(name = "type", type = QueryType.EQUAL)
	@Schema(description = "参数键类型")
	private Integer type;

	@QueryField(name = "remark", type = QueryType.LIKE)
	@Schema(description = "备注")
	private String remark;

}
