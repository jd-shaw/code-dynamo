package com.shaw.sys.core.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.commons.validation.ValidationGroup;
import com.shaw.sys.core.dto.SystemParameterDto;
import com.shaw.sys.core.param.SystemParameterParam;
import com.shaw.sys.core.query.SystemParameterQuery;
import com.shaw.sys.core.service.SystemParameterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 系统参数
 *
 * @author shaw
 * @date 2021/10/25
 */
@Getter
@Tag(name = "系统参数")
@RestController
@RequestMapping("/system/param")
@RequiredArgsConstructor
public class SystemParamController {

	private final SystemParameterService systemParameterService;

	@Operation(summary = "添加")
	@PostMapping("/add")
	public ResResult<Void> add(@RequestBody SystemParameterParam param) {
		ValidationUtil.validateParam(param, ValidationGroup.add.class);
		getSystemParameterService().add(param);
		return Res.ok();
	}

	@Operation(summary = "更新")
	@PostMapping("/update")
	public ResResult<Void> update(@RequestBody SystemParameterParam param) {
		ValidationUtil.validateParam(param, ValidationGroup.edit.class);
		getSystemParameterService().update(param);
		return Res.ok();
	}

	@Operation(summary = "分页")
	@GetMapping("/page")
	public ResResult<PageResult<SystemParameterDto>> page(@ParameterObject PageParam pageParam,
			@ParameterObject SystemParameterQuery param) {
		return Res.ok(getSystemParameterService().page(param));
	}

	@Operation(summary = "获取单条")
	@GetMapping("/find-by-id")
	public ResResult<SystemParameterDto> findById(@Parameter(description = "主键") String id) {
		return Res.ok(getSystemParameterService().findById(id));
	}

	@Operation(summary = "删除")
	@DeleteMapping("/delete")
	public ResResult<Void> delete(String id) {
		getSystemParameterService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "判断编码是否存在")
	@GetMapping("/exists-by-key")
	public ResResult<Boolean> existsByKey(String key) {
		return Res.ok(getSystemParameterService().existsByKey(key));
	}

	@Operation(summary = "判断编码是否存在(不包含自己)")
	@GetMapping("/exists-by-key-not-id")
	public ResResult<Boolean> existsByKeyNotId(String key, String id) {
		return Res.ok(getSystemParameterService().existsByKey(key, id));
	}

	@IgnoreAuth
	@Operation(summary = "根据键名获取键值")
	@GetMapping("/find-by-paramkey")
	public ResResult<String> findByParamKey(String key) {
		return Res.ok(getSystemParameterService().findByParamKey(key));
	}

}
