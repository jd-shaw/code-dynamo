package com.shaw.iam.core.user.dao;

import com.shaw.iam.dto.user.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shaw.iam.core.user.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * 用户信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, String> {

	UserInfo findByEmail(String email);

	UserInfo findByPhone(String phone);

	UserInfo findByUsername(String username);

}
