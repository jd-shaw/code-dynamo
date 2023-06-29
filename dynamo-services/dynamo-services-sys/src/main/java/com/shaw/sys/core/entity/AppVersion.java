package com.shaw.sys.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.mysql.jpa.po.BaseDomain;
import com.shaw.sys.core.covert.AppVersionConvert;
import com.shaw.sys.core.dto.AppVersionDto;
import com.shaw.sys.core.param.AppVersionParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * App版本
 *
 * @author shaw
 * @date 2023/06/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "sys_app_version")
public class AppVersion extends BaseDomain implements EntityBaseFunction<AppVersionDto> {

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

	public static AppVersion init(AppVersionDto in) {
		return AppVersionConvert.CONVERT.convert(in);
	}

	public static AppVersion init(AppVersionParam in) {
		return AppVersionConvert.CONVERT.convert(in);
	}

	@Override
	public AppVersionDto toDto() {
		return AppVersionConvert.CONVERT.convert(this);
	}

}
