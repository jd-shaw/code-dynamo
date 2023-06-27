package com.shaw.iam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.iam.core.permission.service.PermMenuService;
import com.shaw.iam.core.upms.service.RolePermService;
import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.param.permission.PermMenuParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "菜单权限资源")
@RestController
@RequestMapping("/perm/menu")
@RequiredArgsConstructor
public class PermMenuController {

	private final PermMenuService permissionService;

	private final RolePermService rolePermissionService;

	@Operation(summary = "添加菜单权限")
	@PostMapping("/add")
	public ResResult<PermMenuDto> add(@RequestBody PermMenuParam param) {
		return Res.ok(permissionService.add(param));
	}

	@Operation(summary = "修改菜单权限")
	@PostMapping("/update")
	public ResResult<PermMenuDto> update(@RequestBody PermMenuParam param) {
		return Res.ok(permissionService.update(param));
	}

	@Operation(summary = "获取菜单树")
	@GetMapping("/menuTree")
	public ResResult<List<PermMenuDto>> menuTree(String clientCode) {
		return Res.ok();
	}

	@Operation(summary = "获取全部树")
	@GetMapping("/allTree")
	public ResResult<List<PermMenuDto>> allTree(String clientCode) {
		return Res.ok();
	}

	@Operation(summary = "资源(权限码)列表")
	@GetMapping("/resourceList")
	public ResResult<List<PermMenuDto>> resourceList(Long menuId) {
		return Res.ok();
	}

	@Operation(summary = "根据id查询")
	@GetMapping("/findById")
	public ResResult<PermMenuDto> findById(Long id) {
		return Res.ok();
	}

	@Operation(summary = "删除")
	@DeleteMapping("/delete")
	public ResResult<Void> delete(Long id) {
		return Res.ok();
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/existsByPermCode")
	public ResResult<Boolean> existsByPermCode(String permCode) {
		return Res.ok();
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/existsByPermCodeNotId")
	public ResResult<Boolean> existsByPermCode(String permCode, Long id) {
		return Res.ok();
	}

}
