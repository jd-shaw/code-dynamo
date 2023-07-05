package com.shaw.quartz.dto;

import com.shaw.commons.rest.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/7/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "定时任务")
public class QuartzJobDto extends BaseDto {

    @Schema(description = "任务名称")
    private String name;

    @Schema(description = "任务类名")
    private String jobClassName;

    @Schema(description = "cron表达式")
    private String cron;

    @Schema(description = "参数")
    private String parameter;

    @Schema(description = "状态")
    private Integer state;

    @Schema(description = "备注")
    private String remark;

}
