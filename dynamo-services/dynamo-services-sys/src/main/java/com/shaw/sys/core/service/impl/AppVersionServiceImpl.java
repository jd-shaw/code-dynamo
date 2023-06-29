package com.shaw.sys.core.service.impl;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.sys.core.param.DictionaryParam;
import org.springframework.stereotype.Service;

import com.shaw.commons.exception.DataNotExistException;
import com.shaw.sys.core.dao.AppVersionDao;
import com.shaw.sys.core.dto.AppVersionDto;
import com.shaw.sys.core.entity.AppVersion;
import com.shaw.sys.core.param.AppVersionParam;
import com.shaw.sys.core.service.AppVersionService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 版本管理
 *
 * @author shaw
 * @date 2023/06/28
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class AppVersionServiceImpl implements AppVersionService {

	private final AppVersionDao appVersionDao;

	/**
	 * 添加
	 */
	@Override
	public AppVersionDto add(AppVersionParam param) {
		AppVersion appVersion = AppVersion.init(param);
		return getAppVersionDao().save(appVersion).toDto();
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(String id) {
		getAppVersionDao().deleteById(id);
	}

	/**
	 * 检查更新
	 */
	@Override
	public AppVersionDto check() {
		//		Optional<AppVersion> appVersion = getAppVersionDao().findLatest();
		//		return appVersion.map(AppVersion::toDto).orElseThrow(DataNotExistException::new);
		return null;
	}

	/**
	 * 获取详情
	 */
	@Override
	public AppVersionDto findById(String id) {
		return getAppVersionDao().findById(id).map(AppVersion::toDto).orElseThrow(DataNotExistException::new);
	}

	@Override
	public PageResult<AppVersionDto> page(PageParam pageParam, DictionaryParam clientParam) {
		return null;
	}
}
