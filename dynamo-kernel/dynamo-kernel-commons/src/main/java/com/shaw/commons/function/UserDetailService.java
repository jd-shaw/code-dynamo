package com.shaw.commons.function;

import java.util.Optional;

import com.shaw.commons.entity.UserDetail;

/**
 * 获取用户
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserDetailService {

	/**
	 * 获取用户信息
	 */
	Optional<UserDetail> findByUserId(Long userId);

}
