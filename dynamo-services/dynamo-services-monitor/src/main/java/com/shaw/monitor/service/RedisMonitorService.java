package com.shaw.monitor.service;

import com.shaw.monitor.entity.RedisMonitorResult;

/**
 * @author shaw
 * @date 2023/8/11
 */
public interface RedisMonitorService {
    RedisMonitorResult getRedisInfo();
}
