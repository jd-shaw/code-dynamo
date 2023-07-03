package com.shaw.file.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.shaw.commons.annotation.IgnoreAuth;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.file.dto.UpdateFileDto;
import com.shaw.file.service.FileUploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 文件上传
 *
 * @author shaw
 * @date 2023/06/20
 */
@IgnoreAuth
@Tag(name = "文件上传")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FIleUpLoadController {

	private final FileUploadService uploadService;

	//	@IgnoreAuth(ignore = false)
	//	@Operation(summary = "分页")
	//	@GetMapping("/page")
	//	public ResResult<PageResult<UpdateFileDto>> page(PageParam pageParam) {
	//		return Res.ok(uploadService.page(pageParam));
	//	}

	@IgnoreAuth(ignore = false, login = true)
	@Operation(summary = "上传")
	@PostMapping("/upload")
	public ResResult<UpdateFileDto> local(MultipartFile file, String fileName) throws IOException {
		return Res.ok(uploadService.upload(file, fileName));
	}

	@Operation(summary = "获取文件预览地址")
	@GetMapping("/get-file-preview-url")
	public ResResult<String> getFilePreviewUrl(Long id) {
		return Res.ok(uploadService.getFilePreviewUrl(id));
	}

	@Operation(summary = "获取文件预览地址前缀")
	@GetMapping("/get-file-preview-url-prefix")
	public ResResult<String> getFilePreviewUrlPrefix() {
		return Res.ok(uploadService.getFilePreviewUrlPrefix());
	}

	@Operation(summary = "获取文件下载地址")
	@GetMapping("/get-file-download-url")
	public ResResult<String> getFileDownloadUrl(Long id) {
		return Res.ok(uploadService.getFileDownloadUrl(id));
	}

	@Operation(summary = "预览文件")
	@GetMapping("/preview/{id}")
	public void preview(@PathVariable String id, HttpServletResponse response) {
		uploadService.preview(id, response);
	}

	@Operation(summary = "下载文件")
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable String id) throws IOException {
		return uploadService.download(id);
	}

}
