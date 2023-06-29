package com.shaw.iam.core.upms.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户数据权限关联关系
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserDataScopeService {

	boolean existsByDataScopeId(String dataScopeId);

	void deleteByDataScopeId(String dataScopeId);
}
