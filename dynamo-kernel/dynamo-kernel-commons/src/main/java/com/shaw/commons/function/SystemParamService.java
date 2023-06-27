package com.shaw.commons.function;

/**
 * 参数获取服务(必须有实现类)
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface SystemParamService {

    /**
     * 获取 参数值
     */
    String getValue(String key);

}
