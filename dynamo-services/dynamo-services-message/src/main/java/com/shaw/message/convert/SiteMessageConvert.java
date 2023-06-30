package com.shaw.message.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shaw.message.dto.SiteMessageDto;
import com.shaw.message.entity.SiteMessage;

/**
 * 站内信转换
 *
 * @author shaw
 * @date 2023/06/30
 */
@Mapper
public interface SiteMessageConvert {

	SiteMessageConvert CONVERT = Mappers.getMapper(SiteMessageConvert.class);

	SiteMessageDto convert(SiteMessage in);

}
