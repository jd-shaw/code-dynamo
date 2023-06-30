package com.shaw.iam.core.user.service;

import java.util.List;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.dto.user.LoginAfterUserInfo;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.param.user.UserInfoParam;
import com.shaw.iam.param.user.UserRegisterParam;

/**
 * @author shaw
 * @date 2023/06/20
 */
public interface UserInfoService {

	void register(UserRegisterParam param);

	void save(UserInfoParam param);

	UserInfoDto update(UserInfoParam param);

	void updatePassword(String password, String newPassword);

	void updatePasswordById(String userId, String newPassword);

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

	UserInfoDto findById(String id);

	PageResult<UserInfoDto> page(PageParam pageParam, UserInfoParam param);

	void updateStatus(String id, int statusCode);
}
