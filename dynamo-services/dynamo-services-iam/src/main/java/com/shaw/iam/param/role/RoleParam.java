package com.shaw.iam.param.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "角色")
public class RoleParam {

    @Schema(description = "角色id")
    private Long id;

    @Schema(description = "角色code")
    private String code;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "描述")
    private String remark;

}
