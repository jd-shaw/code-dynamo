package com.shaw.quartz.service.impl;

import com.shaw.quartz.dao.QuartzJobDao;
import com.shaw.quartz.service.QuartzJobService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class QuartzJobServiceImpl implements QuartzJobService {

    private final QuartzJobDao quartzJobDao;

}
