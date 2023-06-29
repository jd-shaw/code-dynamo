package com.shaw.sys.core.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * app版本参数
 *
 * @author shaw
 * @date 2023/06/28
 */
@Data
@Accessors(chain = true)
@Schema(title = "app版本参数")
public class AppVersionParam {

	private Long id;

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
