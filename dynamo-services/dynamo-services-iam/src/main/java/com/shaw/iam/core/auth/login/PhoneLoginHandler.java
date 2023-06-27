package com.shaw.iam.core.auth.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.shaw.auth.authentication.AbstractAuthentication;
import com.shaw.auth.code.AuthLoginTypeCode;
import com.shaw.auth.entity.AuthInfoResult;
import com.shaw.auth.entity.LoginAuthContext;
import com.shaw.iam.core.user.dao.UserInfoDao;
import com.shaw.iam.core.user.entity.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 手机号登录
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PhoneLoginHandler implements AbstractAuthentication {

	// 手机号
	private final String phoneParameter = "phone";

	// 短信验证码
	private final String captchaParameter = "smsCaptcha";

	// 手机验证码类型
	private final String smsCaptchaType = "login";

	private final UserInfoDao userInfoDao;

	@Override
	public String getLoginType() {
		return AuthLoginTypeCode.PHONE;
	}

	/**
	 * 认证
	 */
	@Override
	public AuthInfoResult attemptAuthentication(LoginAuthContext context) {
		// todo
		HttpServletRequest request = context.getRequest();
		String phone = request.getParameter(phoneParameter);
		String captcha = request.getParameter(captchaParameter);

		// 比较验证码是否正确
		//        if (!validateSmsCaptcha(phone, captcha, smsCaptchaType)) {
		//            throw new LoginFailureException(phone, "短信验证码不正确");
		//        }
		// 获取用户信息
		UserInfo userInfo = new UserInfo();
		//                userInfoDao.findByPhone(phone)
		//            .orElseThrow(() -> new LoginFailureException(phone, "手机号不存在"));

		//        deleteSmsCaptcha(phone, smsCaptchaType);
		return new AuthInfoResult().setUserDetail(userInfo.toUserDetail()).setId(userInfo.getId());
	}

}
