package com.shaw.sys.core.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.shaw.redis.RedisClient;
import com.shaw.sys.core.dto.CaptchaDataResult;
import com.shaw.sys.core.service.CaptchaService;
import com.shaw.utils.RandomUIDUtils;
import com.wf.captcha.ArithmeticCaptcha;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 验证码服务
 *
 * @author shaw
 * @date 2023/06/28
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

	/** redis key前缀 */
	private final String IMG_CAPTCHA_PREFIX = "login:captcha:img";
	/** 手机验证码前缀 */
	private final String SMS_CAPTCHA_PREFIX = "phone:captcha:";
	/** 邮箱验证码前缀 */
	private final String EMAIL_CAPTCHA_PREFIX = "email:captcha:";

	private final RedisClient redisClient;

	/**
	 * 获取图片验证码
	 */
	@Override
	public CaptchaDataResult imgCaptcha() {
		String key = RandomUIDUtils.getNumberUID(16);
		// 几位数运算，默认是两位
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(105, 35);
		// 获取运算的结果
		String text = captcha.text();
		log.info("获取图片验证码: {}", text);
		redisClient.setWithTimeout(IMG_CAPTCHA_PREFIX + key, text, 5 * 60 * 1000);
		return new CaptchaDataResult().setCaptchaKey(key).setCaptchaData(captcha.toBase64());
	}

	/**
	 * 校验图片验证码
	 */
	@Override
	public boolean validateImgCaptcha(String key, String captcha) {
		// 比较验证码是否正确
		String captchaByRedis = redisClient.get(IMG_CAPTCHA_PREFIX + key);
		return Objects.equals(captcha, captchaByRedis);
	}

	/**
	 * 失效图片验证码
	 */
	@Override
	public void deleteImgCaptcha(String key) {
		redisClient.deleteKey(IMG_CAPTCHA_PREFIX + key);
	}

	/**
	 * 发送手机验证码
	 */
	@Override
	public String sendSmsCaptcha(String phone, long timeoutSec, String type) {
		String captcha = RandomUIDUtils.getNumberUID(6);
		log.info("短信验证码: {}", captcha);
		redisClient.setWithTimeout(getSMS_CAPTCHA_PREFIX(type) + phone, String.valueOf(captcha), timeoutSec * 1000);
		return captcha;
	}

	/**
	 * 手机发送的验证码是否还有效
	 */
	@Override
	public boolean existsSmsCaptcha(String phone, String type) {
		return redisClient.exists(getSMS_CAPTCHA_PREFIX(type) + phone);
	}

	/**
	 * 校验手机验证码
	 */
	@Override
	public boolean validateSmsCaptcha(String phone, String captcha, String type) {
		// 比较验证码是否正确
		String captchaByRedis = redisClient.get(getSMS_CAPTCHA_PREFIX(type) + phone);
		return Objects.equals(captcha, captchaByRedis);
	}

	/**
	 * 失效手机验证码
	 */
	@Override
	public void deleteSmsCaptcha(String phone, String type) {
		redisClient.deleteKey(getSMS_CAPTCHA_PREFIX(type) + phone);
	}

	/**
	 * 获取手机验证码前缀
	 */
	@Override
	public String getSMS_CAPTCHA_PREFIX(String type) {
		return SMS_CAPTCHA_PREFIX + type + ":";
	}

	/**
	 * 发送邮箱验证码
	 */
	@Override
	public String sendEmailCaptcha(String email, long timeoutSec, String type) {
		String captcha = RandomUIDUtils.getNumberUID(6);
		log.info("邮箱验证码: {}", captcha);
		redisClient.setWithTimeout(getEMAIL_CAPTCHA_PREFIX(type) + email, String.valueOf(captcha), timeoutSec * 1000);
		return captcha;
	}

	/**
	 * 邮箱发送的验证码是否还有效
	 */
	@Override
	public boolean existsEmailCaptcha(String email, String type) {
		return redisClient.exists(getEMAIL_CAPTCHA_PREFIX(type) + email);
	}

	/**
	 * 校验邮箱验证码
	 */
	@Override
	public boolean validateEmailCaptcha(String email, String captcha, String type) {
		// 比较验证码是否正确
		String captchaByRedis = redisClient.get(getEMAIL_CAPTCHA_PREFIX(type) + email);
		return Objects.equals(captcha, captchaByRedis);
	}

	/**
	 * 失效邮箱验证码
	 */
	@Override
	public void deleteEmailCaptcha(String email, String type) {
		redisClient.deleteKey(getEMAIL_CAPTCHA_PREFIX(type) + email);
	}

	/**
	 * 获取邮箱验证码前缀
	 */
	private String getEMAIL_CAPTCHA_PREFIX(String type) {
		return EMAIL_CAPTCHA_PREFIX + type + ":";
	}

}
