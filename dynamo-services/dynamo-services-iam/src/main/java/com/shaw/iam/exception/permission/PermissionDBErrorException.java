package com.shaw.iam.exception.permission;

import static com.shaw.iam.code.IamErrorCode.PERMISSION_DB_ERROR;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;

/**
 * @author shaw
 * @date 2023/06/20
 */
public class PermissionDBErrorException extends BaseException implements Serializable {

	private static final long serialVersionUID = -2698918595713722011L;

	public PermissionDBErrorException() {
		super(PERMISSION_DB_ERROR, "用户没有权限.");
	}

}
