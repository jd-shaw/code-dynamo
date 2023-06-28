package com.shaw.file.service;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.shaw.file.code.FileUploadTypeEnum;
import com.shaw.file.entity.UpdateFileInfo;
import com.shaw.file.entity.UploadFileContext;

/**
 * 文件上传接口
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UploadService {

	/**
	 * 判断启用
	 */
	boolean enable(FileUploadTypeEnum type);

	/**
	 * 上传文件
	 */
	UpdateFileInfo upload(MultipartFile file, UploadFileContext context);

	/**
	 * 预览文件
	 */
	void preview(UpdateFileInfo updateFileInfo, HttpServletResponse response);

	/**
	 * 下载文件
	 */
	InputStream download(UpdateFileInfo updateFileInfo);

	/**
	 * 删除文件
	 */
	void delete(UpdateFileInfo updateFileInfo);

}
