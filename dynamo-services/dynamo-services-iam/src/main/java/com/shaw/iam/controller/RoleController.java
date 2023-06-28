package com.shaw.iam.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
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
		getRoleService().add(roleParam);
		return Res.ok();
	}

	@Operation(summary = "删除角色")
	@DeleteMapping(value = "/delete")
	public ResResult<Void> delete(String id) {
		getRoleService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "修改角色（返回角色对象）")
	@PostMapping(value = "/update")
	public ResResult<RoleDto> update(@RequestBody RoleParam roleParam) {
		ValidationUtil.validateParam(roleParam);
		getRoleService().update(roleParam);
		return Res.ok();
	}

	@Operation(summary = "通过ID查询角色")
	@GetMapping(value = "/find-by-id")
	public ResResult<RoleDto> findById(String id) {
		return Res.ok(getRoleService().findById(id));
	}

	@Operation(summary = "查询所有的角色")
	@GetMapping(value = "/find-all")
	public ResResult<List<RoleDto>> findAll() {
		return Res.ok(getRoleService().findAll());
	}

	@Operation(summary = "角色下拉框")
	@GetMapping(value = "/dropdown")
	public ResResult<List<KeyValue>> dropdown() {
		return Res.ok(getRoleService().dropdown());
	}

	@Operation(summary = "分页查询角色")
	@GetMapping(value = "/page")
	public ResResult<PageResult<RoleDto>> page(PageParam pageParam, RoleParam roleParam) {
		return Res.ok(getRoleService().page(pageParam, roleParam));
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/exists-by-code")
	public ResResult<Boolean> existsByCode(String code) {
		return Res.ok(getRoleService().existsByCode(code));
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/exists-by-code-not-id")
	public ResResult<Boolean> existsByCode(String code, String id) {
		return Res.ok(getRoleService().existsByCodeNotId(code, id));
	}

	@Operation(summary = "名称是否被使用")
	@GetMapping("/exists-by-name")
	public ResResult<Boolean> existsByName(String name) {
		return Res.ok(getRoleService().existsByName(name));
	}

	@Operation(summary = "名称是否被使用(不包含自己)")
	@GetMapping("/exists-by-name-not-id")
	public ResResult<Boolean> existsByName(String name, String id) {
		return Res.ok(getRoleService().existsByNameNotId(name, id));
	}

}
