package com.shaw.iam.core.user.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.user.entity.UserInfo;

/**
 * 用户信息
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface UserInfoDao extends JpaRepositoryImplementation<UserInfo, String> {

	UserInfo findByEmail(String email);

	UserInfo findByPhone(String phone);

	UserInfo findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	boolean existsByUsernameAndIdNot(String username, String id);

	boolean existsByEmailAndIdNot(String email, String id);

	boolean existsByPhoneAndIdNot(String phone, String id);

	@Modifying
	@Query("update UserInfo u set u.status = ?2 where u.id = ?1")
	void updateStatusById(String id, int status);
}
