package com.shaw.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.auth.online.OnlineUserDto;
import com.shaw.auth.online.OnlineUserService;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "在线用户")
@RestController
@RequestMapping("/user/online")
@RequiredArgsConstructor
public class OnlineUserController {

	private final OnlineUserService onlineUserService;

	@Operation(summary = "分页")
	@GetMapping("/page")
	public ResResult<PageResult<OnlineUserDto>> page(PageParam pageParam) {
		return Res.ok(onlineUserService.page(pageParam));
	}

	@Operation(summary = "获取单条")
	@GetMapping("/find-by-session-id")
	public ResResult<OnlineUserDto> findBySessionId(String sessionId) {
		return Res.ok(onlineUserService.findBySessionId(sessionId));
	}

	@Operation(summary = "踢人下线")
	@PostMapping("/logout-by-user-id")
	public ResResult<Void> logoutByUserId(Long userId) {
		onlineUserService.logoutByUserId(userId);
		return Res.ok();
	}

}
