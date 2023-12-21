package com.shaw.iam.core.scope.entity;

import com.shaw.mysql.jpa.entity.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * 数据范围用户关联配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "iam_data_scope_user")
@EqualsAndHashCode(callSuper = true)
public class DataScopeUser extends BaseDomain {

    /**
     * 数据范围id
     */
    @ManyToOne
    @JoinColumn(name = "data_scope_id")
    private DataScope dataScope;

    /**
     * 用户id
     */
    private String userId;

}
