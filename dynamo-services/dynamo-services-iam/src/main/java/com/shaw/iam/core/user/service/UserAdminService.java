package com.shaw.iam.core.user.service;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.dto.user.UserInfoWhole;
import com.shaw.iam.param.user.UserInfoParam;
import com.shaw.iam.param.user.UserRegisterParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shaw
 * @date 2023/6/29
 */
public interface UserAdminService {

    PageResult<UserInfoDto> page(PageParam pageParam, UserInfoParam userInfoParam);

    void lock(String userId);

    void lockBatch(List<String> userIds);

    void unlock(String userId);

    void unlockBatch(List<String> userIds);

    UserInfoWhole getUserInfoWhole(String userId);
}
