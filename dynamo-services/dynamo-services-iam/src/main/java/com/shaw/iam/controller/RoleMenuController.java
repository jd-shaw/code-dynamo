package com.shaw.iam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.upms.service.RolePermService;
import com.shaw.iam.dto.upms.MenuAndResourceDto;
import com.shaw.iam.param.upms.RolePermissionParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 角色权限(菜单)关联关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "角色菜单权限关系")
@RestController
@RequestMapping("/role/menu")
@RequiredArgsConstructor
public class RoleMenuController {

	private final RolePermService rolePermService;

	@Operation(summary = "保存请求权限关系")
	@PostMapping("/save")
	public ResResult<Boolean> save(@RequestBody RolePermissionParam param) {
		ValidationUtil.validateParam(param, RolePermissionParam.PermMenu.class);
		return Res.ok(true);
	}

	@Operation(summary = "获取权限菜单id列表,不包含资源权限")
	@GetMapping("/findMenuIds")
	public ResResult<List<Long>> findMenuIds(String clientCode) {
		return Res.ok();
	}

	@Operation(summary = "根据角色id获取关联权限id集合(包含资源和菜单)")
	@GetMapping("/findPermissionIdsByRole")
	public ResResult<List<Long>> findPermissionIdsByRole(Long roleId, String clientCode) {
		return Res.ok();
	}

	@IgnoreAuth
	@Operation(summary = "获取菜单和资源权限")
	@GetMapping("/getPermissions")
	public ResResult<MenuAndResourceDto> getPermissions(String clientCode) {
		return Res.ok(rolePermService.getPermissions(clientCode));
	}

}
