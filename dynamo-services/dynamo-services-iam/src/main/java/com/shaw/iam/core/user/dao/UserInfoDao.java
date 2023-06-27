package com.shaw.iam.core.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaw.iam.core.user.entity.UserInfo;

/**
 * 用户信息
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserInfoDao extends JpaRepository<UserInfo, String> {

}
