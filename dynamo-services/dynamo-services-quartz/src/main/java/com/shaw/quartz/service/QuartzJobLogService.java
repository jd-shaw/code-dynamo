package com.shaw.quartz.service;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.quartz.dto.QuartzJobLogDto;
import com.shaw.quartz.entity.QuartzJobLog;
import com.shaw.quartz.param.QuartzJobLogQuery;

/**
 * @author xjd
 * @date 2023/7/5
 */
public interface QuartzJobLogService {

    void add(QuartzJobLog quartzJobLog);

    PageResult<QuartzJobLogDto> page(PageParam pageParam, QuartzJobLogQuery query);

    QuartzJobLogDto findById(String id);
}
