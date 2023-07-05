package com.shaw.quartz.controller;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.quartz.dto.QuartzJobDto;
import com.shaw.quartz.param.QuartzJobParam;
import com.shaw.quartz.service.QuartzJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务
 *
 * @author shaw
 * @date 2023/7/5
 */
@Tag(name = "定时任务")
@RestController
@RequestMapping("/quartz")
@RequiredArgsConstructor
public class QuartzJobController {

    private final QuartzJobService quartzJobService;

    @Operation(summary = "添加")
    @PostMapping("/add")
    public ResResult<Void> add(@RequestBody QuartzJobParam param) {
        quartzJobService.add(param);
        return Res.ok();
    }

    @Operation(summary = "更新")
    @PostMapping("/update")
    public ResResult<Void> update(@RequestBody QuartzJobParam param) {
        quartzJobService.update(param);
        return Res.ok();
    }

    @Operation(summary = "分页")
    @GetMapping("/page")
    public ResResult<PageResult<QuartzJobDto>> page(PageParam pageParam, QuartzJobParam param) {
        return Res.ok(quartzJobService.page(pageParam, param));
    }

    @Operation(summary = "单条")
    @GetMapping("/find-by-id")
    public ResResult<QuartzJobDto> findById(String id) {
        return Res.ok(quartzJobService.findById(id));
    }

    @Operation(summary = "启动")
    @PostMapping("/start")
    public ResResult<Void> start(String id) {
        quartzJobService.start(id);
        return Res.ok();
    }

    @Operation(summary = "停止")
    @PostMapping("/stop")
    public ResResult<Void> stop(String id) {
        quartzJobService.stop(id);
        return Res.ok();
    }

    @Operation(summary = "立即执行")
    @PostMapping("/execute")
    public ResResult<Void> execute(String id) {
        quartzJobService.execute(id);
        return Res.ok();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/delete")
    public ResResult<Void> delete(String id) {
        quartzJobService.delete(id);
        return Res.ok();
    }

    @Operation(summary = "判断是否是定时任务类")
    @GetMapping("/judge-job-class")
    public ResResult<String> judgeJobClass(String jobClassName) {
        return Res.ok(quartzJobService.judgeJobClass(jobClassName));
    }

    @Operation(summary = "同步定时任务状态")
    @PostMapping("/sync-job-status")
    public ResResult<Void> syncJobStatus() {
        quartzJobService.syncJobStatus();
        return Res.ok();
    }

}
