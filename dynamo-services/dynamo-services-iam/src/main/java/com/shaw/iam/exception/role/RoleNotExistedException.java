package com.shaw.iam.exception.role;

import static com.shaw.iam.code.IamErrorCode.ROLE_NOT_EXISTED;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;

/**
 * 角色不存在
 *
 * @author shaw
 * @date 2023/06/20
 */
public class RoleNotExistedException extends BaseException implements Serializable {

    private static final long serialVersionUID = -6651799569179960110L;

    public RoleNotExistedException() {
        super(ROLE_NOT_EXISTED, "角色不存在.");
    }

}
