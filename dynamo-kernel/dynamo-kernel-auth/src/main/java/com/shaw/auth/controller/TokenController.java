package com.shaw.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.auth.service.TokenService;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Tag(name = "认证相关")
@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {

	private final TokenService tokenService;

	@Operation(summary = "普通登录")
	@PostMapping("/login")
	public ResResult<String> login(HttpServletRequest request, HttpServletResponse response) {
		return Res.ok(getTokenService().login(request, response));
	}

	@Operation(summary = "退出")
	@PostMapping("/logout")
	public ResResult<String> logout() {
		getTokenService().logout();
		return Res.ok();
	}

}
