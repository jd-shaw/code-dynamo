package com.shaw.file.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.file.convert.FileConvert;
import com.shaw.file.dto.UpdateFileDto;
import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 上传文件信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "starter_file_upload_info")
public class UpdateFileInfo extends BaseDomain implements EntityBaseFunction<UpdateFileDto> {

	/** 存储位置 */
	private String filePath;

	/** 文件名称 */
	private String fileName;

	/** 文件类型 */
	private String fileType;

	/** 文件后缀 */
	private String fileSuffix;

	/** 文件大小 */
	private Long fileSize;

	/** 外部存储id */
	private String externalStorageId;

	@Override
	public UpdateFileDto toDto() {
		return FileConvert.CONVERT.convert(this);
	}

}
