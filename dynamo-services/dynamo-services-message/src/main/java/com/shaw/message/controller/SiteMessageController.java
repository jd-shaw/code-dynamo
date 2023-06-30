package com.shaw.message.controller;

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
@Tag(name = "站内信")
@RestController
@RequestMapping("/site/message")
@RequiredArgsConstructor
public class SiteMessageController {

	@Operation(summary = "获取未读的接收消息条数")
	@GetMapping("/count-by-receive-not-read")
	public ResResult<Integer> countByReceiveNotRead() {
		return Res.ok();
	}

}
