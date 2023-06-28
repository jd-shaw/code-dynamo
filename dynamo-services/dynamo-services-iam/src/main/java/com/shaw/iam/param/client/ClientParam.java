package com.shaw.iam.param.client;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Schema(title = "认证应用")
@Accessors(chain = true)
public class ClientParam {

	@Schema(description = "主键")
	private String id;

	@Schema(description = "编码")
	private String code;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "是否系统内置")
	@Column(columnDefinition = "int default 0")
	private int isSystem;

	@Schema(description = "是否可用")
	@Column(columnDefinition = "int default 0")
	private int enable;

	@Schema(description = "关联终端")
	private List<String> loginTypeIdList;

	@Schema(description = "描述")
	private String description;

}
