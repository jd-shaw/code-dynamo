package com.shaw.iam.core.upms.service.impl;

import org.springframework.stereotype.Service;

import com.shaw.iam.core.upms.dao.UserDataScopeDao;
import com.shaw.iam.core.upms.service.UserDataScopeService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xjd
 * @date 2023/6/28
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class UserDataScopeServiceImpl implements UserDataScopeService {

	private final UserDataScopeDao dataScopeDao;

	@Override
	public boolean existsByDataScopeId(String dataScopeId) {
		return getDataScopeDao().existsByDataScopeId(dataScopeId);
	}

	@Override
	public void deleteByDataScopeId(String dataScopeId) {
		getDataScopeDao().deleteByDataScopeId(dataScopeId);
	}
}
