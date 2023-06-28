package com.shaw.file.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.file.dto.UpdateFileDto;
import com.shaw.file.entity.UpdateFileInfo;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Mapper
public interface FileConvert {

	FileConvert CONVERT = Mappers.getMapper(FileConvert.class);

	UpdateFileDto convert(UpdateFileInfo in);

}
