package com.shaw.iam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.param.user.UserDeptBatchParam;
import com.shaw.iam.param.user.UserDeptParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "用户部门关联关系")
@RestController
@RequestMapping("/user/dept")
@RequiredArgsConstructor
public class UserDeptController {

	//	private final UserDeptService userDeptService;

	@Operation(summary = "给用户分配部门")
	@PostMapping("/saveAssign")
	public ResResult<Void> saveAssign(@RequestBody UserDeptParam param) {
		ValidationUtil.validateParam(param);
		//		userDeptService.saveAssign(param.getUserId(), param.getDeptIds());
		return Res.ok();
	}

	@Operation(summary = "给用户分配部门(批量)")
	@PostMapping("/saveAssignBatch")
	public ResResult<Void> saveAssignBatch(@RequestBody UserDeptBatchParam param) {
		ValidationUtil.validateParam(param);
		//		userDeptService.saveAssignBatch(param.getUserIds(), param.getDeptIds());
		return Res.ok();
	}

	//	@Operation(summary = "根据用户ID获取到部门集合")
	//	@GetMapping(value = "/findAllByUser")
	//	public ResResult<List<DeptDto>> findAllByUser(Long id) {
	//		return Res.ok(userDeptService.findDeptListByUser(id));
	//	}
	//
	//	@Operation(summary = "根据用户ID获取到部门id集合")
	//	@GetMapping(value = "/findIdsByUser")
	//	public ResResult<List<Long>> findIdsByUser(Long id) {
	//		return Res.ok(userDeptService.findDeptIdsByUser(id));
	//	}

}
