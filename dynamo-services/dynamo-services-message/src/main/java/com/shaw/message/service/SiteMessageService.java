package com.shaw.message.service;

import com.shaw.message.dto.SiteMessageDto;

/**
 * @author shaw
 * @date 2023/6/30
 */
public interface SiteMessageService {

    SiteMessageDto findById(String id);
}
