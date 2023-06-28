package com.shaw.iam.core.user.service.impl;

import com.shaw.commons.exception.BaseException;
import com.shaw.iam.core.user.entity.UserExpandInfo;
import com.shaw.iam.exception.user.UserInfoNotExistsException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import com.shaw.iam.core.user.dao.UserExpandInfoDao;
import com.shaw.iam.core.user.service.UserExpandInfoService;
import com.shaw.iam.dto.user.UserExpandInfoDto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xjd
 * @date 2023/06/20
 */
@Getter
@Service
@RequiredArgsConstructor
public class UserExpandInfoServiceImpl implements UserExpandInfoService {

	private final UserExpandInfoDao userExpandInfoDao;

	@Override
	public void save(UserExpandInfo userExpandInfo) {
		getUserExpandInfoDao().save(userExpandInfo);
	}

	@Override
	public UserExpandInfoDto findById(String id) {
		return getUserExpandInfoDao().findById(id).orElseThrow(UserInfoNotExistsException::new).toDto();
	}

	@Override
	public void updateChangePasswordTime(String userId, LocalDateTime changePasswordTime) {
		UserExpandInfo userExpandInfo = getUserExpandInfoDao().findById(userId)
				.orElseThrow(UserInfoNotExistsException::new);
		userExpandInfo.setLastChangePasswordTime(changePasswordTime);
		getUserExpandInfoDao().save(userExpandInfo);
	}
}
