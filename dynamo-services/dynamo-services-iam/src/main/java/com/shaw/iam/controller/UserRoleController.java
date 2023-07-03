package com.shaw.iam.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.role.service.RoleService;
import com.shaw.iam.core.upms.service.UserRoleService;
import com.shaw.iam.dto.role.RoleDto;
import com.shaw.iam.param.upms.UserRoleBatchParam;
import com.shaw.iam.param.upms.UserRoleParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Tag(name = "用户角色管理")
@RestController
@RequestMapping("/user/role")
@AllArgsConstructor
public class UserRoleController {

	private final UserRoleService userRoleService;
	private final RoleService roleService;

	@Operation(summary = "给用户分配角色")
	@PostMapping(value = "/save-assign")
	public ResResult<Void> saveAssign(@RequestBody UserRoleParam param) {
		ValidationUtil.validateParam(param);
		userRoleService.saveAssign(param.getUserId(), param.getRoleIds());
		return Res.ok();
	}

	@Operation(summary = "给用户分配角色(批量)")
	@PostMapping(value = "/save-assign-batch")
	public ResResult<Void> saveAssignBatch(@RequestBody UserRoleBatchParam param) {
		ValidationUtil.validateParam(param);
		userRoleService.saveAssignBatch(param.getUserIds(), param.getRoleIds());
		return Res.ok();
	}

	@Operation(summary = "根据用户ID获取到角色集合")
	@GetMapping(value = "/find-roles-by-user")
	public ResResult<List<RoleDto>> findRolesByUser(String id) {
		List<String> roleIdsByUserId = userRoleService.findRoleIdsByUserId(id);
		if (CollectionUtils.isNotEmpty(roleIdsByUserId)) {
			return Res.ok(getRoleService().findByIds(roleIdsByUserId));
		} else
			return Res.ok();
	}

	@Operation(summary = "根据用户ID获取到角色id集合")
	@GetMapping(value = "/find-role-ids-by-user")
	public ResResult<List<String>> findRoleIdsByUser(String id) {
		return Res.ok(getUserRoleService().findRoleIdsByUserId(id));
	}

}
