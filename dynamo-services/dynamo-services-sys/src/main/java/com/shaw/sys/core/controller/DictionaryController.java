package com.shaw.sys.core.controller;

import com.shaw.commons.utils.ValidationUtil;
import com.shaw.commons.validation.ValidationGroup;
import com.shaw.sys.core.dto.DictionaryDto;
import com.shaw.sys.core.param.DictionaryParam;
import com.shaw.sys.core.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.sys.core.dto.AppVersionDto;
import com.shaw.sys.core.param.AppVersionParam;
import com.shaw.sys.core.service.AppVersionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 字典
 *
 * @author shaw
 * @date 2023/06/28
 */
@Getter
@Tag(name = "字典")
@RestController
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictionaryController {

	private final DictionaryService dictionaryService;

	@Operation(summary = "添加")
	@PostMapping("/add")
	public ResResult<DictionaryDto> add(@RequestBody DictionaryParam param) {
		ValidationUtil.validateParam(param, ValidationGroup.add.class);
		return Res.ok(dictionaryService.add(param));
	}

	@Operation(summary = "根据id删除")
	@DeleteMapping("/delete")
	public ResResult<Boolean> delete(String id) {
		dictionaryService.delete(id);
		return Res.ok();
	}

	@Operation(summary = "更新")
	@PostMapping("/update")
	public ResResult<DictionaryDto> update(@RequestBody DictionaryParam param) {
		ValidationUtil.validateParam(param, ValidationGroup.edit.class);
		return Res.ok(dictionaryService.update(param));
	}

	@Operation(summary = "根据id获取")
	@GetMapping("/findById")
	public ResResult<DictionaryDto> findById(String id) {
		return Res.ok(dictionaryService.findById(id));
	}

	@Operation(summary = "查询全部")
	@GetMapping("/findAll")
	public ResResult<List<DictionaryDto>> findAll() {
		return Res.ok(dictionaryService.findAll());
	}

	@Operation(summary = "分页")
	@GetMapping("/page")
	public ResResult<PageResult<DictionaryDto>> page(PageParam pageParam, DictionaryParam param) {
		return Res.ok(dictionaryService.page(pageParam, param));
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/existsByCode")
	public ResResult<Boolean> existsByCode(String code) {
		return Res.ok(dictionaryService.existsByCode(code));
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/existsByCodeNotId")
	public ResResult<Boolean> existsByCode(String code, String id) {
		return Res.ok(dictionaryService.existsByCode(code, id));
	}

}
