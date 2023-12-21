package com.shaw.iam.core.permission.entity;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.permission.convert.PermConvert;
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.param.permission.PermPathParam;
import com.shaw.mysql.jpa.entity.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 权限资源(url请求)
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "iam_perm_path")
@EqualsAndHashCode(callSuper = true)
public class PermPath extends BaseDomain implements EntityBaseFunction<PermPathDto> {

    /**
     * 权限标识
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 启用
     */
    private boolean enable;

    /**
     * 是否通过系统生成的权限
     */
    private boolean generate;

    /**
     * 描述
     */
    private String remark;

    public static PermPath init(PermPathParam param) {
        return PermConvert.CONVERT.convert(param);
    }

    @Override
    public PermPathDto toDto() {
        return PermConvert.CONVERT.convert(this);
    }

}
