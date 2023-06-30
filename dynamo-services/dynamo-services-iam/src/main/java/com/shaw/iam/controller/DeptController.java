package com.shaw.iam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.iam.core.dept.service.DeptService;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.dto.dept.DeptTreeResult;
import com.shaw.iam.param.dept.DeptParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Tag(name = "部门管理")
@RestController
@RequestMapping("/dept")
@AllArgsConstructor
public class DeptController {

	private final DeptService deptService;

	@Operation(summary = "添加")
	@PostMapping("/add")
	public ResResult<DeptDto> add(@RequestBody DeptParam param) {
		return Res.ok(getDeptService().add(param));
	}

	@Operation(summary = "获取")
	@GetMapping("/find-by-id")
	ResResult<DeptDto> findById(String id) {
		return Res.ok(getDeptService().findById(id));
	}

	@Operation(summary = "树状展示")
	@GetMapping("/tree")
	public ResResult<List<DeptTreeResult>> tree() {
		return Res.ok(getDeptService().tree());
	}

	@Operation(summary = "更新")
	@PostMapping("/update")
	public ResResult<DeptDto> update(@RequestBody DeptParam param) {
		return Res.ok(getDeptService().update(param));
	}

	@Operation(summary = "普通删除")
	@DeleteMapping("/delete")
	public ResResult<Void> delete(String id) {
		getDeptService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "强制级联删除")
	@DeleteMapping("/delete-and-children")
	public ResResult<Boolean> deleteAndChildren(String id) {
		return Res.ok(getDeptService().deleteAndChildren(id));
	}

}
