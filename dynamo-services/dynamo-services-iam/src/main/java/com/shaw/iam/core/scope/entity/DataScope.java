package com.shaw.iam.core.scope.entity;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.scope.convert.DataScopeConvert;
import com.shaw.iam.dto.scope.DataScopeDto;
import com.shaw.iam.param.scope.DataScopeParam;
import com.shaw.mysql.jpa.entity.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * 数据范围配置
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
@Table(name = "iam_data_scope")
@EqualsAndHashCode(callSuper = true)
public class DataScope extends BaseDomain implements EntityBaseFunction<DataScopeDto> {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    private Set<DataScopeDept> dataScopeDepts;
    private Set<DataScopeUser> dataScopeUsers;

    @OneToMany(mappedBy = "dataScope", fetch = FetchType.LAZY)
    public Set<DataScopeDept> getDataScopeDepts() {
        return dataScopeDepts;
    }

    @OneToMany(mappedBy = "dataScope", fetch = FetchType.LAZY)
    public Set<DataScopeUser> getDataScopeUsers() {
        return dataScopeUsers;
    }


    public static DataScope init(DataScopeParam in) {
        return DataScopeConvert.CONVERT.convert(in);
    }


    @Override
    public DataScopeDto toDto() {
        return DataScopeConvert.CONVERT.convert(this);
    }

}
