package com.shaw.file.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上传文件信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
public class UpdateFileDto {

	/** 主键 */
	private String id;

	/** 存储位置 */
	private String filePath;

	/** 文件名称 */
	private String fileName;

	/** 文件后缀 */
	private String fileSuffix;

	/** 文件类型 */
	private String fileType;

	/** 文件大小 */
	private Long fileSize;

	/** 外部存储id */
	private String externalStorageId;

	/** 创建者ID */
	private String creator;

	/** 创建时间 */
	private LocalDateTime createTime;

	//    public String getFileSize() {
	//        return FileUtil.readableFileSize(fileSize);
	//    }

}
