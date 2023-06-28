package com.shaw.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shaw.commons.exception.BaseException;
import com.shaw.file.code.FileUploadTypeEnum;
import com.shaw.file.configuration.FileUploadProperties;
import com.shaw.file.dao.UpdateFileDao;
import com.shaw.file.dto.UpdateFileDto;
import com.shaw.file.entity.UpdateFileInfo;
import com.shaw.file.entity.UploadFileContext;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.file.FileTypeUtil;
import com.shaw.utils.text.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件上传管理类
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

	private final List<UploadService> uploadServices;

	private final UpdateFileDao updateFileDao;

	private final FileUploadProperties fileUploadProperties;

	//    private final SystemParamService systemParamService;

	/**
	 * 文件上传
	 * @param file 文件
	 * @param fileName 文件名称
	 */
	@Transactional(rollbackFor = Exception.class)
	public UpdateFileDto upload(MultipartFile file, String fileName) throws IOException {
		val uploadType = fileUploadProperties.getUploadType();
		UploadService uploadService = uploadServices.stream().filter(s -> s.enable(uploadType)).findFirst()
				.orElseThrow(() -> new BaseException("未找到该类的上传处理器"));
		if (file.isEmpty()) {
			throw new BaseException("文件不可为空");
		}
		// 上传文件信息
		if (StringUtils.isBlank(fileName)) {
			fileName = file.getOriginalFilename();
		}
		String fileType = FileTypeUtil.getType(fileName);
		String fileSuffix = fileType;

		// 获取不到类型名,后缀名使用上传文件名称的后缀
		if (StringUtils.isBlank(fileSuffix)) {
			fileSuffix = StringUtils.substringAfter(fileName, ".");
		}
		UploadFileContext context = new UploadFileContext().setFileId(RandomUIDUtils.getUUID()).setFileName(fileName)
				.setFileSuffix(fileSuffix);

		UpdateFileInfo uploadInfo = uploadService.upload(file, context);
		uploadInfo.setFileSuffix(fileSuffix).setFileType(fileType).setFileName(fileName);
		uploadInfo.setId(context.getFileId());
		updateFileDao.save(uploadInfo);
		return uploadInfo.toDto();
	}

	/**
	 * 浏览
	 */
	public void preview(String id, HttpServletResponse response) {
		val uploadType = fileUploadProperties.getUploadType();
		UploadService uploadService = uploadServices.stream().filter(s -> s.enable(uploadType)).findFirst()
				.orElseThrow(() -> new BaseException("未找到该类的上传处理器"));
		UpdateFileInfo updateFileInfo = updateFileDao.findById(id).orElseThrow(() -> new BaseException("文件不存在"));
		uploadService.preview(updateFileInfo, response);
	}

	/**
	 * 文件下载
	 */
	public ResponseEntity<byte[]> download(String id) throws IOException {
		FileUploadTypeEnum uploadType = fileUploadProperties.getUploadType();
		UploadService uploadService = uploadServices.stream().filter(s -> s.enable(uploadType)).findFirst()
				.orElseThrow(() -> new BaseException("未找到该类文件的处理器"));
		UpdateFileInfo updateFileInfo = updateFileDao.findById(id).orElseThrow(() -> new BaseException("文件不存在"));
		InputStream inputStream = uploadService.download(updateFileInfo);
		// 设置header信息
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment",
				new String(updateFileInfo.getFileName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
		return new ResponseEntity<>(IOUtils.toByteArray(inputStream), headers, HttpStatus.OK);
	}

	/**
	 * 获取文件字节数组
	 */
	public byte[] getFileBytes(String id) throws IOException {
		val uploadType = fileUploadProperties.getUploadType();
		UploadService uploadService = uploadServices.stream().filter(s -> s.enable(uploadType)).findFirst()
				.orElseThrow(() -> new BaseException("未找到该类文件的处理器"));
		UpdateFileInfo updateFileInfo = updateFileDao.findById(id).orElseThrow(() -> new BaseException("文件不存在"));
		InputStream inputStream = uploadService.download(updateFileInfo);
		return IOUtils.toByteArray(inputStream);
	}

	/**
	 * 分页
	 */
	//	public PageResult<UpdateFileDto> page(PageParam pageParam) {
	//		return MpUtil.convert2DtoPageResult(updateFileDao.page(pageParam));
	//	}

	/**
	 * 获取文件预览地址
	 */
	public String getFilePreviewUrl(Long id) {
		return this.getServerUrl() + "/file/preview/" + id;
	}

	/**
	 * 获取文件预览地址前缀
	 */
	public String getFilePreviewUrlPrefix() {
		return this.getServerUrl() + "/file/preview/";
	}

	/**
	 * 获取文件地址
	 */
	public String getFileDownloadUrl(Long id) {
		return this.getServerUrl() + "/file/download/" + id;
	}

	/**
	 * 服务地址
	 */
	private String getServerUrl() {
		//        String serverUrl = paramService.getValue("FileServerUrl");
		//        if (StrUtil.isBlank(serverUrl)) {
		//            serverUrl = fileUploadProperties.getServerUrl();
		//        }
		return fileUploadProperties.getServerUrl();
	}

}
