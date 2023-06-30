package com.shaw.sys.core.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.sys.core.dto.CaptchaDataResult;
import com.shaw.sys.core.service.CaptchaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 验证码服务
 *
 * @author shaw
 * @date 2023/06/28
 */
@Getter
@Tag(name = "验证码服务")
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

	private final CaptchaService captchaService;

	@IgnoreAuth
	@Operation(summary = "获取图片验证码")
	@PostMapping("/img-captcha")
	public ResResult<CaptchaDataResult> imgCaptcha() {
		return Res.ok(captchaService.imgCaptcha());
	}

}
