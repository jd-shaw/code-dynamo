package com.shaw.sys.core.param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.shaw.commons.validation.ValidationGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统参数
 *
 * @author shaw
 * @date 2023/06/28
 */
@Data
@Accessors(chain = true)
@Schema(title = "系统参数")
public class SystemParameterParam {

	@Null(message = "Id需要为空", groups = ValidationGroup.add.class)
	@NotNull(message = "Id不可为空", groups = ValidationGroup.edit.class)
	@Schema(description = "主键")
	private String id;

	@NotEmpty(message = "参数名称不可为空", groups = ValidationGroup.add.class)
	@Schema(description = "参数名称")
	private String name;

	@NotEmpty(message = "参数键名不可为空", groups = ValidationGroup.add.class)
	@Schema(description = "参数键名")
	private String paramKey;

	@NotEmpty(message = "参数值不可为空", groups = ValidationGroup.add.class)
	@Schema(description = "参数值")
	private String value;

	@NotNull(message = "启用状态不可为空", groups = ValidationGroup.add.class)
	@Schema(description = "启用状态")
	private Boolean enable;

	@Schema(description = "参数键类型")
	private Integer type;

	@Schema(description = "是否是系统参数")
	private boolean internal;

	@Schema(description = "备注")
	private String remark;

}
