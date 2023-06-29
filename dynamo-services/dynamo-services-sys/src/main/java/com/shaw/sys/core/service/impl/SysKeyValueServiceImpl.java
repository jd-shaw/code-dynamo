package com.shaw.sys.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.shaw.commons.rest.dto.KeyValue;
import com.shaw.sys.core.dao.SysKeyValueDao;
import com.shaw.sys.core.entity.SysKeyValue;
import com.shaw.sys.core.service.SysKeyValueService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * kv存储
 *
 * @author shaw
 * @date 2022/3/30
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class SysKeyValueServiceImpl implements SysKeyValueService {

	private final SysKeyValueDao sysKeyValueDao;

	/**
	 * 获取值
	 */
	@Override
	public String get(String key) {
		return getSysKeyValueDao().findByKey(key).map(SysKeyValue::getValue).orElse(null);
	}

	/**
	 * 获取多个
	 */
	@Override
	public List<KeyValue> gets(List<String> keys) {
		return getSysKeyValueDao().findByKeyIn(keys).stream().map(SysKeyValue::toKeyValue).collect(Collectors.toList());
	}

	/**
	 * 设置值
	 */
	@Override
	public void setup(String key, String value) {
		SysKeyValue sysKeyValue = getSysKeyValueDao().findByKey(key)
				.orElse(new SysKeyValue().setKey(key).setValue(value));
		getSysKeyValueDao().save(sysKeyValue);
	}

	/**
	 * 设置多个
	 */
	@Override
	public void setupBatch(List<KeyValue> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			list.forEach(keyValue -> {
				setup(keyValue.getKey(), keyValue.getValue());
			});
		}
	}

}
