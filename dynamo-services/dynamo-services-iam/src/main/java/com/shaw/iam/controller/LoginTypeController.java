package com.shaw.iam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.core.client.service.LoginTypeService;
import com.shaw.iam.dto.client.LoginTypeDto;
import com.shaw.iam.param.client.LoginTypeParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 登录方式
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Tag(name = "登录方式管理")
@RestController
@RequestMapping("/loginType")
@RequiredArgsConstructor
public class LoginTypeController {

	private final LoginTypeService loginTypeService;

	@Operation(summary = "添加登录方式")
	@PostMapping(value = "/add")
	public ResResult<LoginTypeDto> add(@RequestBody LoginTypeParam param) {
		return Res.ok(getLoginTypeService().add(param));
	}

	@Operation(summary = "修改登录方式（返回登录方式对象）")
	@PostMapping(value = "/update")
	public ResResult<LoginTypeDto> update(@RequestBody LoginTypeParam param) {
		return Res.ok(getLoginTypeService().update(param));
	}

	@Operation(summary = "删除登录方式")
	@DeleteMapping(value = "/delete")
	public ResResult<Void> delete(String id) {
		getLoginTypeService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "通过ID查询登录方式")
	@GetMapping(value = "/find-by-id")
	public ResResult<LoginTypeDto> findById(String id) {
		return Res.ok(getLoginTypeService().findById(id));
	}

	@IgnoreAuth
	@Operation(summary = "通过code查询登录方式")
	@GetMapping(value = "/find-by-code")
	public ResResult<LoginTypeDto> findByCode(String code) {
		return Res.ok(getLoginTypeService().findByCode(code));
	}

	@Operation(summary = "查询所有的登录方式")
	@GetMapping(value = "/find-all")
	public ResResult<List<LoginTypeDto>> findAll() {
		return Res.ok(getLoginTypeService().findAll());
	}

	@Operation(summary = "分页查询登录方式")
	@GetMapping(value = "/page")
	public ResResult<PageResult<LoginTypeDto>> page(PageParam pageParam, LoginTypeParam loginTypeParam) {
		return Res.ok(getLoginTypeService().page(pageParam, loginTypeParam));
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/exists-by-code")
	public ResResult<Boolean> existsByCode(String code) {
		return Res.ok(getLoginTypeService().existsByCode(code));
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/exists-by-code-not-id")
	public ResResult<Boolean> existsByCode(String code, String id) {
		return Res.ok(getLoginTypeService().existsByCode(code, id));
	}

}
