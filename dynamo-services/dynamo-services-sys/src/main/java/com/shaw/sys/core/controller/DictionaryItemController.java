package com.shaw.sys.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.commons.validation.ValidationGroup;
import com.shaw.sys.core.dto.DictionaryItemDto;
import com.shaw.sys.core.dto.DictionaryItemSimpleDto;
import com.shaw.sys.core.param.DictionaryItemParam;
import com.shaw.sys.core.service.DictionaryItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shaw
 * @date 2023/06/28
 */
@Getter
@Tag(name = "字典项")
@RestController
@RequestMapping("/dict/item")
@AllArgsConstructor
public class DictionaryItemController {

	private final DictionaryItemService dictionaryItemService;

	@Operation(summary = "添加字典项（返回字典项对象）")
	@PostMapping("/add")
	public ResResult<DictionaryItemDto> add(@RequestBody DictionaryItemParam param) {
		ValidationUtil.validateParam(param, ValidationGroup.add.class);
		return Res.ok(getDictionaryItemService().add(param));
	}

	@Operation(summary = "修改字典项（返回字典项对象）")
	@PostMapping(value = "/update")
	public ResResult<DictionaryItemDto> update(@RequestBody DictionaryItemParam param) {
		ValidationUtil.validateParam(param, ValidationGroup.edit.class);
		return Res.ok(getDictionaryItemService().update(param));
	}

	@Operation(summary = "删除字典项")
	@DeleteMapping(value = "/delete")
	public ResResult<Void> delete(String id) {
		getDictionaryItemService().delete(id);
		return Res.ok();
	}

	@Operation(summary = "根据字典项ID查询")
	@GetMapping("/find-by-id")
	public ResResult<DictionaryItemDto> findById(@Parameter(description = "字典项ID") String id) {
		return Res.ok(getDictionaryItemService().findById(id));
	}

	@Operation(summary = "查询指定字典ID下的所有字典项")
	@GetMapping("/find-by-dictionary-id")
	public ResResult<List<DictionaryItemDto>> findByDictionaryId(@Parameter(description = "字典ID") String dictionaryId) {
		return Res.ok(getDictionaryItemService().findByDictionaryId(dictionaryId));
	}

	@Operation(summary = "分页查询指定字典下的字典项")
	@GetMapping("/page-by-dictionary-id")
	public ResResult<PageResult<DictionaryItemDto>> pageByDictionaryId(PageParam pageParam, String dictId) {
		return Res.ok(getDictionaryItemService().page(pageParam, DictionaryItemParam.builder().dictId(dictId).build()));
	}

	@Operation(summary = "获取全部字典项")
	@GetMapping("/find-all")
	public ResResult<List<DictionaryItemSimpleDto>> findAll() {
		return Res.ok(getDictionaryItemService().findAll());
	}

	@IgnoreAuth
	@Operation(summary = "获取启用的字典项列表")
	@GetMapping("/find-all-by-enable")
	public ResResult<List<DictionaryItemSimpleDto>> findAllByEnable() {
		return Res.ok(getDictionaryItemService().findAllByEnable());
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/exists-by-code")
	public ResResult<Boolean> existsByCode(String code, String dictId) {
		return Res.ok(getDictionaryItemService().existsByCode(code, dictId));
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/exists-by-code-not-id")
	public ResResult<Boolean> existsByCode(String code, String dictId, String id) {
		return Res.ok(getDictionaryItemService().existsByCode(code, dictId, id));
	}

}
