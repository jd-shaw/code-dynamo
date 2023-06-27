package com.shaw.iam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.upms.service.RolePathService;
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.param.upms.RolePermissionParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "角色请求权限消息关系")
@RestController
@RequestMapping("/role/path")
@RequiredArgsConstructor
public class RolePathController {

	private final RolePathService rolePathService;

	@Operation(summary = "保存角色请求权限关联关系")
	@PostMapping("/save")
	public ResResult<Void> save(@RequestBody RolePermissionParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "根据用户id获取角色授权(请求权限列表)")
	@GetMapping("/findPathsByUser")
	public ResResult<List<PermPathDto>> findPathsByUser() {
		return Res.ok();
	}

	@Operation(summary = "根据角色id获取关联权限id")
	@GetMapping("/findIdsByRole")
	public ResResult<List<Long>> findIdsByRole(Long roleId) {
		return Res.ok();
	}

}
