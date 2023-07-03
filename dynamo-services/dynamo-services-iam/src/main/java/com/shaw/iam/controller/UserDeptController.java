package com.shaw.iam.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.dept.service.DeptService;
import com.shaw.iam.core.user.service.UserDeptService;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.param.user.UserDeptBatchParam;
import com.shaw.iam.param.user.UserDeptParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Tag(name = "用户部门关联关系")
@RestController
@RequestMapping("/user/dept")
@RequiredArgsConstructor
public class UserDeptController {

	private final UserDeptService userDeptService;
	private final DeptService deptService;

	@Operation(summary = "给用户分配部门")
	@PostMapping("/save-assign")
	public ResResult<Void> saveAssign(@RequestBody UserDeptParam param) {
		ValidationUtil.validateParam(param);
		userDeptService.saveAssign(param.getUserId(), param.getDeptIds());
		return Res.ok();
	}

	@Operation(summary = "给用户分配部门(批量)")
	@PostMapping("/save-assign-batch")
	public ResResult<Void> saveAssignBatch(@RequestBody UserDeptBatchParam param) {
		ValidationUtil.validateParam(param);
		userDeptService.saveAssignBatch(param.getUserIds(), param.getDeptIds());
		return Res.ok();
	}

	@Operation(summary = "根据用户ID获取到部门集合")
	@GetMapping(value = "/find-all-by-user")
	public ResResult<List<DeptDto>> findAllByUser(String id) {
		List<String> deptIdsByUser = userDeptService.findDeptIdsByUser(id);
		if (CollectionUtils.isNotEmpty(deptIdsByUser)) {
			return Res.ok(getDeptService().findByIds(deptIdsByUser));
		}
		return Res.ok();
	}

	@Operation(summary = "根据用户ID获取到部门id集合")
	@GetMapping(value = "/find-ids-by-user")
	public ResResult<List<String>> findIdsByUser(String id) {
		return Res.ok(userDeptService.findDeptIdsByUser(id));
	}

}
