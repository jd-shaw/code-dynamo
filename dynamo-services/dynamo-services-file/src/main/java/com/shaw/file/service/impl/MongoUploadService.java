package com.shaw.file.service.impl;

import java.io.InputStream;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.file.code.FileUploadTypeEnum;
import com.shaw.file.entity.UpdateFileInfo;
import com.shaw.file.entity.UploadFileContext;
import com.shaw.file.service.UploadService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * mongo方式存储文件
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@ConditionalOnClass(name = "org.springframework.data.mongodb.gridfs.GridFsTemplate")
@RequiredArgsConstructor
public class MongoUploadService implements UploadService {

	private final GridFsTemplate gridFsTemplate;

	@Override
	public boolean enable(FileUploadTypeEnum type) {
		return type == FileUploadTypeEnum.MONGO;
	}

	/**
	 * 上传
	 */
	@SneakyThrows
	@Override
	public UpdateFileInfo upload(MultipartFile file, UploadFileContext context) {
		ObjectId store = gridFsTemplate.store(file.getInputStream(), context.getFileName(), file.getContentType());
		return new UpdateFileInfo().setExternalStorageId(store.toString()).setFileSize(file.getSize());
	}

	@SneakyThrows
	@Override
	public void preview(UpdateFileInfo updateFileInfo, HttpServletResponse response) {
		Criteria criteria = Criteria.where("_id").is(updateFileInfo.getExternalStorageId());
		Query query = new Query(criteria);

		GridFSFile gridFSFile = Optional.ofNullable(gridFsTemplate.findOne(query))
				.orElseThrow(DataNotExistException::new);
		GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
		InputStream inputStream = resource.getInputStream();

		// 获取响应输出流
		ServletOutputStream os = response.getOutputStream();
		IOUtils.copy(inputStream, os);
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, updateFileInfo.getFileType());
		IOUtils.close(inputStream);
		IOUtils.close(os);
	}

	@SneakyThrows
	@Override
	public InputStream download(UpdateFileInfo updateFileInfo) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(updateFileInfo.getExternalStorageId()));
		Query query = new Query(criteria);

		GridFSFile gridFSFile = Optional.ofNullable(gridFsTemplate.findOne(query))
				.orElseThrow(DataNotExistException::new);
		GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
		return resource.getInputStream();
	}

	/**
	 * 删除文件
	 */
	@Override
	public void delete(UpdateFileInfo updateFileInfo) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(updateFileInfo.getExternalStorageId()));
		Query query = new Query(criteria);
		gridFsTemplate.delete(query);
	}

}
