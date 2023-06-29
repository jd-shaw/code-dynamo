package com.shaw.sys.core.dto;

import java.io.Serializable;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "数据字典目录")
public class DictionaryDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 8185789462442511856L;

	@Schema(description = "编码")
	private String code;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "启用状态")
	private Boolean enable;

	@Schema(description = "分类标签")
	private String groupTag;

	@Schema(description = "描述")
	private String remark;

}
