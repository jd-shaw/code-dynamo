package com.shaw.sys.core.dto;

import java.io.Serializable;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据字典项
 *
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "数据字典项Dto")
public class DictionaryItemDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 7403674221398097611L;

	@Schema(description = "字典ID")
	private String dictId;

	@Schema(description = "字典编码")
	private String dictCode;

	@Schema(description = "字典项编码")
	private String code;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "启用状态")
	private Boolean enable;

	@Schema(description = "字典项排序")
	private Double sortNo;

	@Schema(description = "备注")
	private String remark;

}
