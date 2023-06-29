package com.shaw.sys.core.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据字典项(简单)
 *
 * @author shaw
 * @date 2023/06/28
 */
@Data
@Accessors(chain = true)
@Schema(title = "数据字典项Dto")
public class DictionaryItemSimpleDto implements Serializable {

	private static final long serialVersionUID = 7403674221398097611L;

	@Schema(description = "字典编码")
	private String dictCode;

	@Schema(description = "字典项编码")
	private String code;

	@Schema(description = "名称")
	private String name;

}
