package com.shaw.iam.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.core.client.service.ClientService;
import com.shaw.iam.dto.client.ClientDto;
import com.shaw.iam.param.client.ClientParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 认证终端
 *
 * @author shaw
 * @date 2023/06/20
 */
@Tag(name = "认证终端")
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	@Operation(summary = "添加")
	@PostMapping(value = "/add")
	public ResResult<Void> add(@RequestBody ClientParam param) {
		clientService.add(param);
		return Res.ok();
	}

	@Operation(summary = "修改")
	@PostMapping(value = "/update")
	public ResResult<Void> update(@RequestBody ClientParam param) {
		clientService.update(param);
		return Res.ok();
	}

	@Operation(summary = "删除")
	@DeleteMapping(value = "/delete")
	public ResResult<Void> delete(String id) {
		clientService.delete(id);
		return Res.ok();
	}

	@Operation(summary = "通过ID查询")
	@GetMapping(value = "/find-by-id")
	public ResResult<ClientDto> findById(String id) {
		return Res.ok(clientService.findById(id));
	}

	@Operation(summary = "查询所有")
	@GetMapping(value = "/find-all")
	public ResResult<List<ClientDto>> findAll() {
		return Res.ok(clientService.findAll());
	}

	@Operation(summary = "分页查询")
	@GetMapping(value = "/page")
	public ResResult<Page<ClientDto>> page(PageParam pageParam, ClientParam clientParam) {
		return Res.ok(clientService.page(pageParam, clientParam));
	}

	@Operation(summary = "编码是否被使用")
	@GetMapping("/exists-by-code")
	public ResResult<Boolean> existsByCode(String code) {
		return Res.ok(clientService.existsByCode(code));
	}

	@Operation(summary = "编码是否被使用(不包含自己)")
	@GetMapping("/exists-by-code-not-id")
	public ResResult<Boolean> existsByCode(String code, String id) {
		return Res.ok(clientService.existsByCode(code, id));
	}

}
