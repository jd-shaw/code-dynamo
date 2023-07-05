package com.shaw.quartz.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
