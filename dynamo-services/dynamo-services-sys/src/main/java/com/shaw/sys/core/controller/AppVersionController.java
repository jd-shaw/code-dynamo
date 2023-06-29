package com.shaw.sys.core.controller;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.sys.core.dto.AppVersionDto;
import com.shaw.sys.core.param.AppVersionParam;
import com.shaw.sys.core.service.AppVersionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/28
 */
@Getter
@Tag(name = "app版本管理")
@RestController
@RequestMapping("/app/version")
@RequiredArgsConstructor
public class AppVersionController {

	private final AppVersionService appVersionService;

	@Operation(summary = "添加")
	@PostMapping("/add")
	public ResResult<AppVersionDto> add(@RequestBody AppVersionParam param) {
		return Res.ok(getAppVersionService().add(param));
	}

	@Operation(summary = "删除")
	@DeleteMapping("/delete")
	public ResResult<Void> delete(String id) {
		getAppVersionService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "检查更新")
	@PostMapping("/check")
	public ResResult<AppVersionDto> check() {
		return Res.ok(getAppVersionService().check());
	}

	@Operation(summary = "分页")
	@GetMapping("/page")
	public ResResult<PageResult<AppVersionDto>> page(PageParam pageParam) {
		return Res.ok(getAppVersionService().page(pageParam, null));
	}

	@Operation(summary = "查询详情")
	@PostMapping("/findById")
	public ResResult<AppVersionDto> findById(String id) {
		return Res.ok(getAppVersionService().findById(id));
	}

}
