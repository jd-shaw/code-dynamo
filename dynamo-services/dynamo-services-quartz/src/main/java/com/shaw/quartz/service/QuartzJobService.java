package com.shaw.quartz.service;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.quartz.dto.QuartzJobDto;
import com.shaw.quartz.param.QuartzJobParam;

/**
 * @author shaw
 * @date 2023/7/5
 */
public interface QuartzJobService {

    void add(QuartzJobParam param);

    void update(QuartzJobParam param);

    PageResult<QuartzJobDto> page(PageParam pageParam, QuartzJobParam param);

    QuartzJobDto findById(String id);

    void start(String id);

    void stop(String id);

    void execute(String id);

    void delete(String id);

    String judgeJobClass(String jobClassName);

    void syncJobStatus();
}
