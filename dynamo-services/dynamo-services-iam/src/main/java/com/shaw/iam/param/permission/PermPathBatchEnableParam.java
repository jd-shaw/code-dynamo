package com.shaw.iam.param.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 请求权限批量启停用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "请求权限批量启停用")
public class PermPathBatchEnableParam {

    @NotEmpty
    @Schema(description = "权限码集合")
    private List<String> permPathIds;

    @Schema(description = "是否启用")
    private boolean enable;

}
