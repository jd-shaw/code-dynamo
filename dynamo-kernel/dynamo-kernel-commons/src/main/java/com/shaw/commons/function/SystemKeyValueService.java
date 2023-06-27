package com.shaw.commons.function;

import java.util.List;

import com.shaw.commons.rest.dto.KeyValue;

/**
 * kv存储接口 (必须实现)
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface SystemKeyValueService {

	/**
	 * 获取值
	 */
	String get(String key);

	/**
	 * 获取多个
	 */
	List<KeyValue> gets(List<String> keys);

	/**
	 * 设置值
	 */
	void setup(String key, String value);

	/**
	 * 设置多个
	 */
	void setupBatch(List<KeyValue> list);

}
