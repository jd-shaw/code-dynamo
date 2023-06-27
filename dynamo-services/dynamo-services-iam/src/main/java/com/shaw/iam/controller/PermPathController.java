package com.shaw.iam.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Validated
@Tag(name = "请求权限资源")
@RestController
@RequestMapping("/perm/path")
@RequiredArgsConstructor
public class PermPathController {

}
