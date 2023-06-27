package com.shaw.iam.core.auth.service;

import org.springframework.stereotype.Service;

import com.shaw.iam.core.user.dao.UserInfoDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证支撑服务
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthAssistService {

	private final UserInfoDao userInfoDao;

	private final String smsCaptchaType = "login";

	/**
	 * 发送短信验证码
	 */
	public void sendSmsCaptcha(String phone) {
		// 判断用户是否存在
		//		UserInfo userInfo = userInfoManager.findByPhone(phone).orElseThrow(UserInfoNotExistsException::new);
		//		if (userInfo.getStatus() != UserStatusCode.NORMAL) {
		//			throw new BaseException("用户状态异常");
		//		}
		// 有效期5分钟
		// 发送验证码
	}

}
