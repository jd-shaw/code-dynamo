package com.shaw.iam.core.user.service;

import com.shaw.iam.dto.user.LoginAfterUserInfo;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.param.user.UserInfoParam;
import com.shaw.iam.param.user.UserRegisterParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shaw
 * @date 2023/06/20
 */
public interface UserInfoService {

	void register(UserRegisterParam param);

	void save(UserInfoParam param);

	void updatePassword(String password, String newPassword);

	UserInfoDto findByEmail(String email);

	UserInfoDto findByPhone(String phone);

	UserInfoDto findByAccount(String account);

	LoginAfterUserInfo getLoginAfterUserInfo();

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	boolean existsByUsernameAndIdNot(String username, String id);

	boolean existsByEmailAndIdNot(String email, String id);

	boolean existsByPhoneAndIdNot(String phone, String id);

	List<UserInfoDto> findByIds(List<String> ids);
}
