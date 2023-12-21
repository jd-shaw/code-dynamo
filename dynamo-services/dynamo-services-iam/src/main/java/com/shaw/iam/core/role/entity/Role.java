package com.shaw.iam.core.role.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.role.convert.RoleConvert;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.param.role.RoleParam;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.utils.RandomUIDUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 角色
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "iam_role")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseDomain implements EntityBaseFunction<RoleDto> {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否系统内置 不能修改
     */
    private boolean internal;

    /**
     * 描述
     */
    private String remark;

    public static Role init(RoleParam in) {
        Role role = RoleConvert.CONVERT.convert(in);
        role.setId(RandomUIDUtils.getUID());
        return role;
    }

    @Override
    public RoleDto toDto() {
        return RoleConvert.CONVERT.convert(this);
    }

}
