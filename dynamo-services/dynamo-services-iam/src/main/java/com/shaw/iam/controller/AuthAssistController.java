package com.shaw.iam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.iam.core.auth.service.AuthAssistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "认证支撑接口")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthAssistController {

	private final AuthAssistService authAssistService;

	@IgnoreAuth
	@Operation(summary = "发送短信验证码")
	@PostMapping("/send-sms-captcha")
	public ResResult<Void> sendSmsCaptcha(String phone) {
		authAssistService.sendSmsCaptcha(phone);
		return Res.ok();
	}

}
