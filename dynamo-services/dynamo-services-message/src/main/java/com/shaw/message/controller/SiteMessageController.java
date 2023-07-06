package com.shaw.message.controller;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.message.dto.SiteMessageInfoDto;
import com.shaw.message.service.SiteMessageService;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/6/30
 */
@Getter
@Tag(name = "站内信")
@RestController
@RequestMapping("/site/message")
@RequiredArgsConstructor
public class SiteMessageController {

	private final SiteMessageService siteMessageService;

	@Operation(summary = "获取未读的接收消息条数")
	@GetMapping("/count-by-receive-not-read")
	public ResResult<Integer> countByReceiveNotRead() {
		return Res.ok();
	}

	@Operation(summary = "接收消息分页")
	@GetMapping("/pageByReceive")
	public ResResult<PageResult<SiteMessageInfoDto>> pageByReceive(PageParam pageParam, SiteMessageInfoDto query) {
		return Res.ok();
	}
}
