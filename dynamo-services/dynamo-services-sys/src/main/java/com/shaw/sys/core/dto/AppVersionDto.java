package com.shaw.sys.core.dto;

import java.io.Serializable;

import com.shaw.commons.rest.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "app版本")
public class AppVersionDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = -7287549085443499458L;

	/** app版本 */
	private String appVersion;

	/** 下载地址 */
	private String url;

	/** 密码 */
	private String password;

	/** 包名 */
	private String bundleId;

	/** 说明 */
	private String description;

}
