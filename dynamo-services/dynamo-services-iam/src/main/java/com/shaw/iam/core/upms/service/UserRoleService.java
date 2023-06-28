package com.shaw.iam.core.upms.service;

import static com.shaw.iam.code.CachingCode.USER_PATH;
import static com.shaw.iam.code.CachingCode.USER_PERM_CODE;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.commons.exception.BaseException;
import com.shaw.iam.core.role.dao.RoleDao;
import com.shaw.iam.core.upms.dao.UserRoleDao;
import com.shaw.iam.core.upms.entity.UserRole;
import com.shaw.iam.core.user.dao.UserInfoDao;
import com.shaw.iam.core.user.entity.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户角色关系
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserRoleService {

    List<String >findRoleIdsByUserId(String userId);

}
