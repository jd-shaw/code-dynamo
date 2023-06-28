package com.shaw.iam.core.user.service.impl;

import com.shaw.commons.exception.BaseException;
import com.shaw.iam.exception.user.UserInfoNotExistsException;
import org.springframework.stereotype.Service;

import com.shaw.iam.core.user.dao.UserExpandInfoDao;
import com.shaw.iam.core.user.service.UserExpandInfoService;
import com.shaw.iam.dto.user.UserExpandInfoDto;

import lombok.RequiredArgsConstructor;

/**
 * @author xjd
 * @date 2023/06/20
 */

@Service
@RequiredArgsConstructor
public class UserExpandInfoServiceImpl implements UserExpandInfoService {

	private final UserExpandInfoDao userExpandInfoDao;

	@Override
	public UserExpandInfoDto findById(String id) {
		return userExpandInfoDao.findById(id).orElseThrow(UserInfoNotExistsException::new).toDto();
	}
}
