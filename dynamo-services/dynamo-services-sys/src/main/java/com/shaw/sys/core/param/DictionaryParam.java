package com.shaw.sys.core.param;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.shaw.commons.validation.ValidationGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/28
 */
@Data
@Accessors(chain = true)
@Schema(title = "数据字典目录")
public class DictionaryParam implements Serializable {

	private static final long serialVersionUID = 8185789462442511856L;

	@Null(message = "Id需要为空", groups = ValidationGroup.add.class)
	@NotNull(message = "Id不可为空", groups = ValidationGroup.edit.class)
	@Schema(description = "主键")
	private String id;

	@NotEmpty(message = "编码不可以为空", groups = ValidationGroup.add.class)
	@Schema(description = "编码")
	private String code;

	@NotEmpty(message = "编码不可以为空", groups = ValidationGroup.add.class)
	@Schema(description = "名称")
	private String name;

	@NotNull(message = "启用状态不可为空", groups = ValidationGroup.add.class)
	@Schema(description = "启用状态")
	private Boolean enable;

	@Schema(description = "分类标签")
	private String groupTag;

	@Schema(description = "描述")
	private String remark;

}
