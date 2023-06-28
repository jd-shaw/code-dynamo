package com.shaw.file.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shaw.commons.exception.BaseException;
import com.shaw.file.code.FileUploadTypeEnum;
import com.shaw.file.configuration.FileUploadProperties;
import com.shaw.file.entity.UpdateFileInfo;
import com.shaw.file.entity.UploadFileContext;
import com.shaw.file.service.UploadService;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.datetime.DateUtils;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 上传文件本地存储
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LocalUploadService implements UploadService {

	private final FileUploadProperties fileUploadProperties;

	@Override
	public boolean enable(FileUploadTypeEnum type) {
		return type == FileUploadTypeEnum.LOCAL;
	}

	/**
	 * 文件上传
	 */
	@SneakyThrows
	@Override
	public UpdateFileInfo upload(MultipartFile file, UploadFileContext context) {
		String fileSuffix = Optional.ofNullable(context.getFileSuffix()).map(s -> "." + s).orElse("");
		String filePath = DateUtils.getCurrDateTime() + "/" + RandomUIDUtils.getUID() + fileSuffix;
		String storePath = fileUploadProperties.getLocal().getLocalPath() + filePath;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(storePath));
		return new UpdateFileInfo().setFilePath(filePath).setFileSize(file.getSize());
	}

	/**
	 * 浏览
	 */
	@SneakyThrows
	@Override
	public void preview(UpdateFileInfo updateFileInfo, HttpServletResponse response) {
		String storePath = fileUploadProperties.getLocal().getLocalPath() + updateFileInfo.getFilePath();
		File file = new File(storePath);
		if (!file.exists()) {
			throw new BaseException("文件不存在");
		}
		FileInputStream is = new FileInputStream(file);
		// 获取响应输出流
		ServletOutputStream os = response.getOutputStream();
		IOUtils.copy(is, os);
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, updateFileInfo.getFileType());
		IOUtils.close(is);
		IOUtils.close(os);
	}

	/**
	 * 下载
	 */
	@SneakyThrows
	@Override
	public InputStream download(UpdateFileInfo updateFileInfo) {
		String storePath = fileUploadProperties.getLocal().getLocalPath() + updateFileInfo.getFilePath();
		File file = new File(storePath);
		if (!file.exists()) {
			throw new BaseException("文件不存在");
		}
		return Files.newInputStream(file.toPath());
	}

	/**
	 * 删除文件
	 */
	@Override
	public void delete(UpdateFileInfo updateFileInfo) {
		String storePath = fileUploadProperties.getLocal().getLocalPath() + updateFileInfo.getFilePath();
		File file = new File(storePath);
		if (file.exists())
			file.deleteOnExit();
	}

}
