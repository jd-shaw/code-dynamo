package com.shaw.iam.exception.role;

import static com.shaw.iam.code.IamErrorCode.ROLE_ALREADY_USED;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;

/**
 * @author shaw
 * @date 2023/06/20
 */
public class RoleAlreadyUsedException extends BaseException implements Serializable {

    private static final long serialVersionUID = 3704932788916299672L;

    public RoleAlreadyUsedException() {
        super(ROLE_ALREADY_USED, "该角色下分配了用户，您无法将其删除.");
    }

}
