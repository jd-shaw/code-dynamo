package com.shaw.iam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.dto.KeyValue;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.role.service.RoleService;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.param.role.RoleParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@Operation(summary = "添加角色（返回角色对象）")
	@PostMapping(value = "/add")
	public ResResult<RoleDto> add(@RequestBody RoleParam roleParam) {
		ValidationUtil.validateParam(roleParam);
		return Res.ok();
	}

	@Operation(summary = "删除角色")
	@DeleteMapping(value = "/delete")
	public ResResult<Void> delete(Long id) {
		return Res.ok();
	}

	@Operation(summary = "修改角色（返回角色对象）")
	@PostMapping(value = "/update")
	public ResResult<RoleDto> update(@RequestBody RoleParam roleParam) {
		ValidationUtil.validateParam(roleParam);
		return Res.ok();
	}

	@Operation(summary = "通过ID查询角色")
	@GetMapping(value = "/findById")
	public ResResult<RoleDto> findById(Long id) {
		return Res.ok();
	}

	@Operation(summary = "查询所有的角色")
	@GetMapping(value = "/findAll")
	public ResResult<List<RoleDto>> findAll() {
		return Res.ok();
	}

	@Operation(summary = "角色下拉框")
	@GetMapping(value = "/dropdown")
	public ResResult<List<KeyValue>> dropdown() {
		return Res.ok();
	}

	@Operation(summary = "分页查询角色")
	@GetMapping(value = "/page")
	public ResResult<PageResult<RoleDto>> page(PageParam pageParam, RoleParam roleParam) {
		return Res.ok();
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/existsByCode")
	public ResResult<Boolean> existsByCode(String code) {
		return Res.ok();
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/existsByCodeNotId")
	public ResResult<Boolean> existsByCode(String code, Long id) {
		return Res.ok();
	}

	@Operation(summary = "名称是否被使用")
	@GetMapping("/existsByName")
	public ResResult<Boolean> existsByName(String name) {
		return Res.ok();
	}

	@Operation(summary = "名称是否被使用(不包含自己)")
	@GetMapping("/existsByNameNotId")
	public ResResult<Boolean> existsByName(String name, Long id) {
		return Res.ok();
	}

}
