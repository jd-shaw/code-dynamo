package com.shaw.iam.core.user.service;

import java.time.LocalDateTime;

import com.shaw.iam.core.user.entity.UserExpandInfo;
import com.shaw.iam.dto.user.UserExpandInfoDto;

/**
 * @author shaw
 * @date 2023/06/20
 */
public interface UserExpandInfoService {

	void save(UserExpandInfo userExpandInfo);

	UserExpandInfoDto findById(String id);

	void updateChangePasswordTime(String userId, LocalDateTime changePasswordTime);
}
