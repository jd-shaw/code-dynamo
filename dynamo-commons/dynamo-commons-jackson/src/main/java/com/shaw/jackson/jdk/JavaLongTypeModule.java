package com.shaw.jackson.jdk;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Long 类型序列化为String
 *
 * @author shaw
 * @date 2023/06/20
 */
public class JavaLongTypeModule extends SimpleModule {

	public JavaLongTypeModule() {
		// 将 Long 转 String
		this.addSerializer(Long.TYPE, ToStringSerializer.instance);
		this.addSerializer(Long.class, ToStringSerializer.instance);
	}

}
