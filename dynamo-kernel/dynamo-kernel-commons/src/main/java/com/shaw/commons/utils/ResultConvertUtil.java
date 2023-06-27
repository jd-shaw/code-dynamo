package com.shaw.commons.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.shaw.commons.function.EntityBaseFunction;

import lombok.experimental.UtilityClass;

/**
 * 结果转换工具类工具类
 */
@UtilityClass
public class ResultConvertUtil {

    /**
     * list转换
     */
    public <T> List<T> dtoListConvert(List<? extends EntityBaseFunction<T>> list) {
        return list.stream().map(EntityBaseFunction::toDto).collect(Collectors.toList());
    }

    /**
     * 转换成Dto对象
     * @param object 原始对象
     * @param <T> 它的Dto对象
     * @return T
     */
    public <T> T dtoConvert(EntityBaseFunction<T> object) {
        return Optional.ofNullable(object).map(EntityBaseFunction::toDto).orElse(null);
    }

    /**
     * 转换成Dto对象
     * @param optional 原始对象
     * @param <T> 它的Dto对象
     * @return T
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public <T> T dtoConvert(Optional<? extends EntityBaseFunction<T>> optional) {
        return optional.map(EntityBaseFunction::toDto).orElse(null);
    }

}
