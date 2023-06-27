package com.shaw.iam.core.permission.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

/**
 * 请求权限
 *
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
public class RequestPath {

	/** 请求类型 */
	private String requestType;

	/** 请求路径 */
	private String path;

	/** 请求方法名 */
	private String methodName;

	/** 请求方法描述 */
	private String methodRemark;

	/** 请求类名 */
	private String className;

	/** 请求类名 */
	private String classFullName;

	/** 请求类描述 */
	private String classRemark;

}
