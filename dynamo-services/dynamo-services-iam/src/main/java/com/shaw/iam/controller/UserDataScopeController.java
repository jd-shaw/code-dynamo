package com.shaw.iam.controller;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.scope.service.DataScopeService;
import com.shaw.iam.core.upms.service.UserDataScopeService;
import com.shaw.iam.dto.scope.DataScopeDto;
import com.shaw.iam.param.upms.UserDataScopeBatchParam;
import com.shaw.iam.param.upms.UserDataScopeParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/07/02
 */
@Getter
@Tag(name = "用户数据权限配置")
@RestController
@RequestMapping("/user/data/scope")
@RequiredArgsConstructor
public class UserDataScopeController {

	private final DataScopeService dataScopeService;
	private final UserDataScopeService userDataScopeService;

	@Operation(summary = "给用户分配权限")
	@PostMapping("/save-assign")
	public ResResult<Void> saveAssign(@RequestBody UserDataScopeParam param) {
		ValidationUtil.validateParam(param);
		getUserDataScopeService().saveAssign(param.getUserId(), param.getDataScopeId());
		return Res.ok();
	}

	@Operation(summary = "给用户分配权限(批量)")
	@PostMapping("/save-assign-batch")
	public ResResult<Void> saveAssignBatch(@RequestBody UserDataScopeBatchParam param) {
		getUserDataScopeService().saveAssignBatch(param.getUserIds(), param.getDataScopeId());
		return Res.ok();
	}

	@Operation(summary = "根据用户ID获取到数据权限")
	@GetMapping(value = "/find-data-scope-by-user")
	public ResResult<DataScopeDto> findDataScopeByUser(String id) {
		return Res.ok(getDataScopeService().findDataScopeByUserId(id));
	}

	@Operation(summary = "根据用户ID获取到数据权限Id")
	@GetMapping(value = "/find-data-scope-id-by-user")
	public ResResult<String> findDataScopeIdByUser(String id) {
		return Res.ok(getUserDataScopeService().findDataScopeIdByUserId(id));
	}

}
