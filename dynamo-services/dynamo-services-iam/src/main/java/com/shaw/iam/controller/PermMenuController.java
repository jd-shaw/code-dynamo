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
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
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
	@GetMapping("/menu-tree")
	public ResResult<List<PermMenuDto>> menuTree(String clientCode) {
		return Res.ok(getPermissionService().findMenuTree(clientCode));
	}

	@Operation(summary = "获取全部树")
	@GetMapping("/all-tree")
	public ResResult<List<PermMenuDto>> allTree(String clientCode) {
		return Res.ok(getPermissionService().findAllTree(clientCode));
	}

	@Operation(summary = "资源(权限码)列表")
	@GetMapping("/resource-list")
	public ResResult<List<PermMenuDto>> resourceList(String menuId) {
		return Res.ok(getPermissionService().findResourceByMenuId(menuId));
	}

	@Operation(summary = "根据id查询")
	@GetMapping("/find-by-id")
	public ResResult<PermMenuDto> findById(String id) {
		return Res.ok(getPermissionService().findById(id));
	}

	@Operation(summary = "删除")
	@DeleteMapping("/delete")
	public ResResult<Void> delete(String id) {
		getPermissionService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/exists-by-perm-code")
	public ResResult<Boolean> existsByPermCode(String permCode) {
		return Res.ok(getPermissionService().existsByPermCode(permCode));
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/exists-by-perm-code-not-id")
	public ResResult<Boolean> existsByPermCode(String permCode, String id) {
		return Res.ok(getPermissionService().existsByPermCodeAndIdNot(permCode, id));
	}

}
