package com.shaw.monitor.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xjd
 * @date 2023/12/14
 */
@Tag(name = "系统信息监控")
@RestController
@RequestMapping("/system/monitor")
@RequiredArgsConstructor
public class SystemMonitorController {

}
