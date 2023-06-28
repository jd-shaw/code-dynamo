package com.shaw.iam.core.user.service;

import com.shaw.iam.core.user.entity.UserExpandInfo;
import com.shaw.iam.dto.user.UserExpandInfoDto;

/**
 * @author xjd
 * @date 2023/06/20
 */
public interface UserExpandInfoService {

	UserExpandInfoDto findById(String id);

}
