package com.shaw.quartz.entity;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.quartz.convert.QuartzJobConvert;
import com.shaw.quartz.dto.QuartzJobLogDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author shaw
 * @date 2023/7/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "quartz_job_log")
public class QuartzJobLog extends BaseDomain implements EntityBaseFunction<QuartzJobLogDto> {

    /**
     * 处理器的名字
     */
    private String handlerName;

    /**
     * 处理器全限定名
     */
    private String className;

    /**
     * 是否执行成功
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 执行时长
     */
    private Long duration;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @Override
    public QuartzJobLogDto toDto() {
        return QuartzJobConvert.CONVERT.convert(this);
    }

}
