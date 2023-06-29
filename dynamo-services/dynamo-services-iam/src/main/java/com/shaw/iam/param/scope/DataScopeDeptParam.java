package com.shaw.iam.param.scope;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "部门范围")
public class DataScopeDeptParam {

    @Schema(description = "数据权限id")
    private String dataScopeId;

    @Schema(description = "部门id集合")
    private List<String> deptIds;

}
