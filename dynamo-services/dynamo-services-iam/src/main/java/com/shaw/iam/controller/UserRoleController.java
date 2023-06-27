package com.shaw.iam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.param.upms.UserRoleBatchParam;
import com.shaw.iam.param.upms.UserRoleParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "用户角色管理")
@RestController
@RequestMapping("/user/role")
@AllArgsConstructor
public class UserRoleController {

	private final UserRoleService userRoleService;

	@Operation(summary = "给用户分配角色")
	@PostMapping(value = "/saveAssign")
	public ResResult<Void> saveAssign(@RequestBody UserRoleParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "给用户分配角色(批量)")
	@PostMapping(value = "/saveAssignBatch")
	public ResResult<Void> saveAssignBatch(@RequestBody UserRoleBatchParam param) {
		ValidationUtil.validateParam(param);
		return Res.ok();
	}

	@Operation(summary = "根据用户ID获取到角色集合")
	@GetMapping(value = "/findRolesByUser")
	public ResResult<List<RoleDto>> findRolesByUser(Long id) {
		return Res.ok();
	}

	@Operation(summary = "根据用户ID获取到角色id集合")
	@GetMapping(value = "/findRoleIdsByUser")
	public ResResult<List<Long>> findRoleIdsByUser(Long id) {
		return Res.ok();
	}

}
