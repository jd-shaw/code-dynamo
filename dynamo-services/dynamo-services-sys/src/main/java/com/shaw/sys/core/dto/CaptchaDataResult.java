package com.shaw.sys.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 验证码数据
 *
 * @author shaw
 * @date 2023/06/28
 */
@Data
@Accessors(chain = true)
@Schema(title = "验证码数据")
public class CaptchaDataResult {

	@Schema(description = "验证码标示")
	private String captchaKey;

	@Schema(description = "验证码base64数据")
	private String captchaData;

}
