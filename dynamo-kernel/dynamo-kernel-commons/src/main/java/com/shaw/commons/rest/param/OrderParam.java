package com.shaw.commons.rest.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 排序参数
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Setter
@Schema(title = "分页查询参数")
public class OrderParam {

	@Schema(description = "排序字段")
	private String sortField;

	@Schema(description = "是否升序")
	private boolean asc = true;

}
