package com.shaw.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.auth.endpoint.TokenService;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * @author xjd
 * @date 2023/6/27
 */
@Tag(name = "认证相关")
@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {

	private final TokenService tokenService;

	@Operation(summary = "普通登录")
	@PostMapping("/login")
	public ResResult<String> login(HttpServletRequest request, HttpServletResponse response) {
		return Res.ok(tokenService.login(request, response));
	}

	@Operation(summary = "退出")
	@PostMapping("/logout")
	public ResResult<String> logout() {
		tokenService.logout();
		return Res.ok();
	}

}
