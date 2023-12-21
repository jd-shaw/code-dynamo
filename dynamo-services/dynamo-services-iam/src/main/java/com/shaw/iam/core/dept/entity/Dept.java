package com.shaw.iam.core.dept.entity;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.code.OrgCategoryCode;
import com.shaw.iam.core.dept.convert.DeptConvert;
import com.shaw.iam.core.scope.entity.DataScopeDept;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.param.dept.DeptParam;
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
 * 部门表
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
@Table(name = "iam_dept")
@EqualsAndHashCode(callSuper = true)
public class Dept extends BaseDomain implements EntityBaseFunction<DeptDto> {

    /**
     * 父机构ID
     */
    private String parentId;

    /**
     * 机构/部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Double sortNo;

    /**
     * 机构类别
     *
     * @see OrgCategoryCode
     */
    private Integer orgCategory;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 传真
     */
    private String fax;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    private Set<DataScopeDept> dataScopeDepts;

    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    public Set<DataScopeDept> getDataScopeDepts() {
        return dataScopeDepts;
    }

    public static Dept init(DeptParam in) {
        return DeptConvert.CONVERT.convert(in);
    }

    @Override
    public DeptDto toDto() {
        return DeptConvert.CONVERT.convert(this);
    }

}
