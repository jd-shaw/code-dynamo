package com.shaw.file.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.shaw.file.code.FileUploadTypeEnum;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 文件上传配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "dynamo.starter.file-upload")
public class FileUploadProperties {

	/**
	 * 上传文件类型 默认为本地
	 */
	private FileUploadTypeEnum uploadType = FileUploadTypeEnum.LOCAL;

	/** 服务器地址,优先级低于系统参数配置 */
	private String serverUrl = "http://127.0.0.1:8000";

	/** 本地存储 */
	private Local local = new Local();

	/** mongo存储配置 */
	private Mongo mongo = new Mongo();

	/** minio存储配置 */
	private Minio minio = new Minio();

	/**
	 * 本地存储
	 */
	@Getter
	@Setter
	public static class Local {

		/** 本地存储路径 */
		private String localPath = "/data/file/";

	}

	/**
	 * mongo存储配置
	 */
	@Getter
	@Setter
	public static class Mongo {

		/** 存储桶 */
		private String bucket = "fs";

	}

	/**
	 * Minio存储配置
	 */
	@Getter
	@Setter
	public static class Minio {

		/** 端点地址 */
		private String endpoint;

		/** 区域 */
		private String region;

		/** 访问 key */
		private String accessKey;

		/** 访问 Secret */
		private String accessSecret;

		/** 存储桶 需要至少三位 */
		private String bucket = "dynamo";

	}

}
