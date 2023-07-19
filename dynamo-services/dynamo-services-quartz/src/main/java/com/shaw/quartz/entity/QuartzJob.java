package com.shaw.quartz.entity;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.quartz.convert.QuartzJobConvert;
import com.shaw.quartz.dto.QuartzJobDto;
import com.shaw.quartz.param.QuartzJobParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author shaw
 * @date 2023/7/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "quartz_job")
public class QuartzJob extends BaseDomain implements EntityBaseFunction<QuartzJobDto> {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类名
     */
    private String jobClassName;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 参数
     */
    private String parameter;

    /**
     * 状态
     * QuartzJobCode
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    @Override
    public QuartzJobDto toDto() {
        return QuartzJobConvert.CONVERT.convert(this);
    }

    public static QuartzJob init(QuartzJobParam in) {
        return QuartzJobConvert.CONVERT.convert(in);
    }
}
