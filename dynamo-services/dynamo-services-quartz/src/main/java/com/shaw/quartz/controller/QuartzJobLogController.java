package com.shaw.quartz.controller;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.quartz.dto.QuartzJobLogDto;
import com.shaw.quartz.param.QuartzJobLogQuery;
import com.shaw.quartz.service.QuartzJobLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Tag(name = "定时任务执行日志")
@RestController
@RequestMapping("/quartz/log")
@RequiredArgsConstructor
public class QuartzJobLogController {

    private final QuartzJobLogService quartzJobLogService;

    @Operation(summary = "分页")
    @GetMapping("/page")
    public ResResult<PageResult<QuartzJobLogDto>> page(PageParam pageParam, QuartzJobLogQuery param) {
        return Res.ok(quartzJobLogService.page(pageParam, param));
    }

    @Operation(summary = "单条")
    @GetMapping("/find-by-id")
    public ResResult<QuartzJobLogDto> findById(String id) {
        return Res.ok(quartzJobLogService.findById(id));
    }

}
