package com.shaw.iam.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ValidationUtil;
import com.shaw.iam.core.permission.service.PermPathService;
import com.shaw.iam.dto.permission.PermPathDto;
import com.shaw.iam.param.permission.PermPathBatchEnableParam;
import com.shaw.iam.param.permission.PermPathParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Validated
@Tag(name = "请求权限资源")
@RestController
@RequestMapping("/perm/path")
@RequiredArgsConstructor
public class PermPathController {

    private final PermPathService permPathService;

    @Operation(summary = "添加权限")
    @PostMapping("/add")
    public ResResult<PermPathDto> add(@RequestBody PermPathParam param) {
        getPermPathService().save(param);
        return Res.ok();
    }

    @Operation(summary = "更新权限")
    @PostMapping("/update")
    public ResResult<PermPathDto> update(@RequestBody PermPathParam param) {
        getPermPathService().update(param);
        return Res.ok();
    }

    @Operation(summary = "批量更新状态")
    @PostMapping("/batch-update-enable")
    public ResResult<PermPathDto> batchUpdateEnable(@RequestBody PermPathBatchEnableParam param) {
        ValidationUtil.validateParam(param);
        getPermPathService().batchUpdateEnable(param);
        return Res.ok();
    }

    @Operation(summary = "删除权限")
    @DeleteMapping("/delete")
    public ResResult<Void> delete(@NotNull(message = "id不可为空") String id) {
        getPermPathService().delete(id);
        return Res.ok();
    }

    @Operation(summary = "批量删除")
    @DeleteMapping("/delete-batch")
    public ResResult<Void> deleteBatch(@RequestBody @NotEmpty(message = "id集合不可为空") List<String> ids) {
        getPermPathService().delete(ids);
        return Res.ok();
    }

    @Operation(summary = "获取详情")
    @GetMapping("/find-by-id")
    public ResResult<PermPathDto> findById(String id) {
        return Res.ok(getPermPathService().findById(id));
    }

    @Operation(summary = "权限分页")
    @GetMapping("/page")
    public ResResult<PageResult<PermPathDto>> page(PageParam pageParam, PermPathParam param) {
        return Res.ok(getPermPathService().page(pageParam, param));
    }

    @Operation(summary = "权限列表")
    @GetMapping("/find-all")
    public ResResult<List<PermPathDto>> findAll() {
        return Res.ok(getPermPathService().findAll());
    }

	//    @Operation(summary = "同步系统请求资源")
	//    @PostMapping("/syncSystem")
	//    public ResResult<Void> syncSystem() {
	//        getPermPathService().syncSystem();
	//        return Res.ok();
	//    }


}
