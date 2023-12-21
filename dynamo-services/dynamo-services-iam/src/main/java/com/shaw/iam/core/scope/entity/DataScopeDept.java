package com.shaw.iam.core.scope.entity;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.dept.entity.Dept;
import com.shaw.iam.core.scope.convert.DataScopeDeptCovert;
import com.shaw.iam.dto.scope.DataScopeDeptDto;
import com.shaw.mysql.jpa.entity.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * 数据范围部门关联配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Entity
@SuperBuilder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "iam_data_scope_dept")
@EqualsAndHashCode(callSuper = true)
public class DataScopeDept extends BaseDomain implements EntityBaseFunction<DataScopeDeptDto> {

    /**
     * 数据范围id
     */
    @ManyToOne
    @JoinColumn(name = "data_scope_id")
    private DataScope dataScope;

    /**
     * 部门id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @Override
    public DataScopeDeptDto toDto() {
        return DataScopeDeptCovert.CONVERT.convert(this);
    }
}
