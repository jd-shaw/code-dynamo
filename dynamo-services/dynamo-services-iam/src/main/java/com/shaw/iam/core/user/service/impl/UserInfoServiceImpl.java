package com.shaw.iam.core.user.service.impl;

import org.springframework.stereotype.Service;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.iam.core.user.dao.UserInfoDao;
import com.shaw.iam.core.user.entity.UserInfo;
import com.shaw.iam.core.user.service.UserExpandInfoService;
import com.shaw.iam.core.user.service.UserInfoService;
import com.shaw.iam.dto.user.LoginAfterUserInfo;
import com.shaw.iam.dto.user.UserExpandInfoDto;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.exception.user.UserInfoNotExistsException;

import lombok.RequiredArgsConstructor;

/**
 * @author xjd
 * @date 2023/06/20
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

	private final UserInfoDao userInfoDao;
	private final UserExpandInfoService userExpandInfoService;

	@Override
	public UserInfoDto findByEmail(String email) {
		return userInfoDao.findByEmail(email).toDto();
	}

	@Override
	public UserInfoDto findByPhone(String phone) {
		return userInfoDao.findByPhone(phone).toDto();
	}

	@Override
	public UserInfoDto findByAccount(String account) {
		return userInfoDao.findByUsername(account).toDto();
	}

	@Override
	public LoginAfterUserInfo getLoginAfterUserInfo() {
		UserInfo userInfo = userInfoDao.findById(SecurityUtil.getUserId()).orElseThrow(UserInfoNotExistsException::new);
		UserExpandInfoDto userExpandInfo = userExpandInfoService.findById(SecurityUtil.getUserId());
		return new LoginAfterUserInfo().setAvatar(userExpandInfo.getAvatar()).setUserId(userInfo.getId())
				.setUsername(userInfo.getUsername()).setName(userInfo.getName());
	}
}
