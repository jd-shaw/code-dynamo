package com.shaw.iam.exception.role;

import static com.shaw.iam.code.IamErrorCode.ROLE_ALREADY_EXISTED;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;

/**
 * @author shaw
 * @date 2023/06/20
 */
public class RoleAlreadyExistedException extends BaseException implements Serializable {

	private static final long serialVersionUID = -9126473695763034719L;

	public RoleAlreadyExistedException() {
		super(ROLE_ALREADY_EXISTED, "角色已经存在.");
	}

}
