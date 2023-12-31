package com.shaw.iam.dto.client;

import java.util.ArrayList;
import java.util.List;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "认证应用")
@Accessors(chain = true)
public class ClientDto extends BaseDto {

	@Schema(description = "编码")
	private String code;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "是否系统内置")
	private int isSystem;

	@Schema(description = "是否可用")
	private int enable;

	@Schema(description = "关联登录方式id")
	private List<String> loginTypeIdList = new ArrayList<>();

	@Schema(description = "描述")
	private String description;

}
