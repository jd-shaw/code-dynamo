package com.shaw.message.service.impl;

import org.springframework.stereotype.Service;

import com.shaw.commons.exception.DataNotExistException;
import com.shaw.message.dao.SiteMessageDao;
import com.shaw.message.dto.SiteMessageDto;
import com.shaw.message.entity.SiteMessage;
import com.shaw.message.service.SiteMessageService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/6/30
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class SiteMessageServiceImpl implements SiteMessageService {

	private final SiteMessageDao siteMessageDao;

	/**
	 * 查询详情
	 */
	@Override
	public SiteMessageDto findById(String id) {
		return getSiteMessageDao().findById(id).map(SiteMessage::toDto).orElseThrow(DataNotExistException::new);
	}

	//	/**
	//	 * 未读消息数量
	//	 */
	//	public Integer countByReceiveNotRead(SiteMessageInfoDto query) {
	//		// Long userId = 0L;
	//		return getSiteMessageDao().countByReceiveNotRead(SecurityUtil.getUserId());
	//	}

}
