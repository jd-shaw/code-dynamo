package com.shaw.iam.core.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.user.entity.UserInfo;

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

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	boolean existsByUsernameAndIdNot(String username, String id);

	boolean existsByEmailAndIdNot(String email, String id);

	boolean existsByPhoneAndIdNot(String phone, String id);
}
