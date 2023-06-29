package com.shaw.sys.core.dto;

import java.io.Serializable;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统参数
 *
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "系统参数")
public class SystemParameterDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 7403674221398097611L;

    @Schema(description = "参数名称")
    private String name;

    @Schema(description = "参数键名")
    private String paramKey;

    @Schema(description = "参数值")
    private String value;

    @Schema(description = "参数类型")
    private Integer type;

    @Schema(description = "启用状态")
    private Boolean enable;

    @Schema(description = "是否系统参数")
    private boolean internal;

    @Schema(description = "备注")
    private String remark;

}
