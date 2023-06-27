package com.shaw.commons.rest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 基础Dto
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 2985535678913619503L;

	@Schema(description = "主键")
	private String id;

	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	@Schema(description = "最后修改时间")
	private LocalDateTime lastModifiedTime;

	@Schema(description = "版本号")
	private Integer version;

}
