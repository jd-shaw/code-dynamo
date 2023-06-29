package com.shaw.iam.core.scope.service;

import com.shaw.iam.code.CachingCode;
import com.shaw.iam.dto.scope.DataScopeUserInfoDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据范围权限限定用户级别
 *
 * @author shaw
 * @date 2023/06/20
 */

public interface DataScopeUserService {

	void deleteByDataScopeId(String dataScopeId);

	List<DataScopeUserInfoDto> findUsersByDataScopeId(String dataScopeId);

	void saveUserAssign(String dataScopeId, List<String> userIds);

	void deleteBatch(List<String> ids);
}
