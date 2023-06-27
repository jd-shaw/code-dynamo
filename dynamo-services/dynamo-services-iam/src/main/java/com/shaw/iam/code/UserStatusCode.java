package com.shaw.iam.code;

/**
 * 用户状态码
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserStatusCode {

    /** 正常 */
    int NORMAL = 1;

    /** 多次登录失败被锁定 */
    int LOCK = 2;

    /** 封禁 */
    int BAN = 3;

}
