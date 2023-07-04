package com.shaw.iam.controller;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.core.scope.service.DataScopeService;
import com.shaw.iam.core.scope.service.DataScopeUserService;
import com.shaw.iam.dto.scope.DataScopeDto;
import com.shaw.iam.dto.scope.DataScopeUserInfoDto;
import com.shaw.iam.param.scope.DataScopeDeptParam;
import com.shaw.iam.param.scope.DataScopeParam;
import com.shaw.iam.param.scope.DataScopeUserParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Tag(name = "数据范围权限配置")
@RestController
@RequestMapping("/data/scope")
@RequiredArgsConstructor
public class DataScopeController {

	private final DataScopeService dataScopeService;
	private final DataScopeUserService dataScopeUserService;

	@Operation(summary = "添加")
	@PostMapping("/add")
	public ResResult<Void> add(@RequestBody DataScopeParam param) {
		getDataScopeService().add(param);
		return Res.ok();
	}

	@Operation(summary = "更新")
	@PostMapping("/update")
	public ResResult<Void> update(@RequestBody DataScopeParam param) {
		getDataScopeService().update(param);
		return Res.ok();
	}

	@Operation(summary = "删除")
	@DeleteMapping("/delete")
	public ResResult<Void> delete(String id) {
		getDataScopeService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "保存关联部门")
	@PostMapping("/save-dept-assign")
	public ResResult<Void> saveDeptAssign(@RequestBody DataScopeDeptParam param) {
		getDataScopeService().saveDeptAssign(param);
		return Res.ok();
	}

	@Operation(summary = "获取关联部门id")
	@GetMapping("/get-dept-ids")
	public ResResult<List<String>> getDeptIds(String id) {
		return Res.ok(getDataScopeService().findDeptIds(id));
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/exists-by-code")
	public ResResult<Boolean> existsByCode(String code) {
		return Res.ok(getDataScopeService().existsByCode(code));
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/exists-by-code-not-id")
	public ResResult<Boolean> existsByCode(String code, String id) {
		return Res.ok(getDataScopeService().existsByCodeAndIdNot(code, id));
	}

	@Operation(summary = "名称是否被使用")
	@GetMapping("/exists-by-name")
	public ResResult<Boolean> existsByName(String name) {
		return Res.ok(getDataScopeService().existsByName(name));
	}

	@Operation(summary = "名称是否被使用(不包含自己)")
	@GetMapping("/exists-by-name-not-id")
	public ResResult<Boolean> existsByName(String name, String id) {
		return Res.ok(getDataScopeService().existsByNameAndIdNot(name, id));
	}

	@Operation(summary = "获取")
	@GetMapping("/find-by-id")
	public ResResult<DataScopeDto> findById(String id) {
		return Res.ok(getDataScopeService().findById(id));
	}

	@Operation(summary = "分页")
	@GetMapping("/page")
	public ResResult<PageResult<DataScopeDto>> page(@ParameterObject PageParam pageParam,
			@ParameterObject DataScopeParam param) {
		return Res.ok(getDataScopeService().page(pageParam, param));
	}

	@Operation(summary = "查询全部")
	@GetMapping("/find-all")
	public ResResult<List<DataScopeDto>> findAll() {
		return Res.ok(getDataScopeService().findAll());
	}

	@Operation(summary = "获取关联的用户列表")
	@GetMapping("/find-users-by-data-scope-id")
	public ResResult<List<DataScopeUserInfoDto>> findUsersByDataScopeId(String id) {
		return Res.ok(getDataScopeUserService().findUsersByDataScopeId(id));
	}

	@Operation(summary = "保存关联用户权限")
	@PostMapping("/save-user-assign")
	public ResResult<Void> saveUserAssign(@RequestBody DataScopeUserParam param) {
		getDataScopeUserService().saveUserAssign(param.getDataScopeId(), param.getUserIds());
		return Res.ok();
	}

	@Operation(summary = "批量删除关联用户")
	@DeleteMapping("/delete-user-assigns")
	public ResResult<Void> deleteUserAssigns(@RequestBody List<String> ids) {
		dataScopeUserService.deleteBatch(ids);
		return Res.ok();
	}

}
