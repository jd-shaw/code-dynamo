package com.shaw.file.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上传文件上下文
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
public class UploadFileContext {

	/** 文件id */
	private String fileId;

	/** 文件名称 */
	private String fileName;

	/** 文件后缀名 */
	private String fileSuffix;

}
