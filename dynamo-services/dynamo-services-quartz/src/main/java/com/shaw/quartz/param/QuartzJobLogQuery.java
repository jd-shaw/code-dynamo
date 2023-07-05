package com.shaw.quartz.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Data
@Accessors(chain = true)
@Schema(title = "定时任务日志查询")
public class QuartzJobLogQuery {

    @Schema(description = "处理器全限定名")
    private String className;

    @Schema(description = "是否执行成功")
    private Boolean success;

}
