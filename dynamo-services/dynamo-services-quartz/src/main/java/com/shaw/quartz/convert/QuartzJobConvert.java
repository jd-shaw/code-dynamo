package com.shaw.quartz.convert;

import com.shaw.quartz.dto.QuartzJobDto;
import com.shaw.quartz.dto.QuartzJobLogDto;
import com.shaw.quartz.entity.QuartzJob;
import com.shaw.quartz.entity.QuartzJobLog;
import com.shaw.quartz.param.QuartzJobParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Mapper
public interface QuartzJobConvert {

    QuartzJobConvert CONVERT = Mappers.getMapper(QuartzJobConvert.class);

    QuartzJobDto convert(QuartzJob in);

    QuartzJob convert(QuartzJobParam in);

    QuartzJobLogDto convert(QuartzJobLog in);

}
