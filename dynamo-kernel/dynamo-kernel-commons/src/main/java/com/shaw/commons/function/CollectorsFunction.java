package com.shaw.commons.function;

/**
 * stream流处理时进行Collectors时的函数
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface CollectorsFunction {

	/**
	 * 转换成map时, 相同key的对象保留第一个
	 */
	static <T> T retainFirst(T o1, T o2) {
		return o1;
	}

	/**
	 * 转换成map时, 相同key的对象保留最后一个
	 */
	static <T> T retainLatest(T o1, T o2) {
		return o2;
	}

}
