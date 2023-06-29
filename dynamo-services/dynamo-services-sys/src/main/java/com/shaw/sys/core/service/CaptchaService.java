package com.shaw.sys.core.service;

import com.shaw.sys.core.dto.CaptchaDataResult;

/**
 * 验证码服务
 *
 * @author shaw
 * @date 2023/06/28
 */
public interface CaptchaService {

    CaptchaDataResult imgCaptcha();

    boolean validateImgCaptcha(String key, String captcha);

    void deleteImgCaptcha(String key);

    String sendSmsCaptcha(String phone, long timeoutSec, String type);

    boolean existsSmsCaptcha(String phone, String type);

    boolean validateSmsCaptcha(String phone, String captcha, String type);

    void deleteSmsCaptcha(String phone, String type);

    String getSMS_CAPTCHA_PREFIX(String type);

    String sendEmailCaptcha(String email, long timeoutSec, String type);

    boolean existsEmailCaptcha(String email, String type);

    boolean validateEmailCaptcha(String email, String captcha, String type);

    void deleteEmailCaptcha(String email, String type);
}
