package com.shaw.iam.core.user.service;

import com.shaw.iam.dto.user.LoginAfterUserInfo;
import com.shaw.iam.dto.user.UserInfoDto;

/**
 * @author xjd
 * @date 2023/06/20
 */
public interface UserInfoService {

	UserInfoDto findByEmail(String email);

	UserInfoDto findByPhone(String phone);

	UserInfoDto findByAccount(String account);

	LoginAfterUserInfo getLoginAfterUserInfo();
}
