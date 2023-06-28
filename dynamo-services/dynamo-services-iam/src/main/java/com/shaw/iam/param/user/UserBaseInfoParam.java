package com.shaw.iam.param.user;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户基础信息")
public class UserBaseInfoParam {

	@Schema(description = "用户id")
	private Long id;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "性别")
	private Integer sex;

	@Schema(description = "头像")
	private String avatar;

	@Schema(description = "生日")
	private LocalDate birthday;

}