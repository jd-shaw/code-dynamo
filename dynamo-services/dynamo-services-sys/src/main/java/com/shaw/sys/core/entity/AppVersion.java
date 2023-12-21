package com.shaw.sys.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.sys.core.covert.AppVersionConvert;
import com.shaw.sys.core.dto.AppVersionDto;
import com.shaw.sys.core.param.AppVersionParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * App版本
 *
 * @author shaw
 * @date 2023/06/28
 */
@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "sys_app_version")
@EqualsAndHashCode(callSuper = true)
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
