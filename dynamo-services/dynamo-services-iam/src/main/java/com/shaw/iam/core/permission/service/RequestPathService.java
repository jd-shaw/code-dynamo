package com.shaw.iam.core.permission.service;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.google.common.collect.Lists;
import com.shaw.iam.core.permission.entity.RequestPath;
import com.shaw.utils.text.StringUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求权限处理
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestPathService {

    private final WebApplicationContext applicationContext;

    private final static String REQUEST_MAPPING_HANDLER_MAPPING = "requestMappingHandlerMapping";

    /**
     * 获取系统请求列表
     */
    public List<RequestPath> getRequestPaths() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(REQUEST_MAPPING_HANDLER_MAPPING,
                RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<RequestPath> requestPathList = new ArrayList<>(map.size());
        for (RequestMappingInfo requestMappingInfo : map.keySet()) {
            // 根据请求方式和路径构建请求权限对象
            List<RequestPath> requestPaths = this.builderRequestPaths(requestMappingInfo);
            if (CollectionUtils.isEmpty(requestPaths)) {
                continue;
            }

            HandlerMethod handlerMethod = map.get(requestMappingInfo);
            Method method = handlerMethod.getMethod();
            Class<?> beanClass = method.getDeclaringClass();
            // 请求方法名
            String methodName = method.getName();
            // 请求描述
            String summary = this.getSummary(method);
            // beanClass 名称 / 描述
            String className = beanClass.getSimpleName();
            String classFullName = beanClass.getName();
            String classRemark = this.getTagName(beanClass);
            for (RequestPath requestPath : requestPaths) {
                requestPath.setClassName(className)
                    .setClassFullName(classFullName)
                    .setClassRemark(classRemark)
                    .setMethodName(methodName)
                    .setMethodRemark(summary);
            }
            requestPathList.addAll(requestPaths);
        }
        return requestPathList;
    }

    /**
     * 获取请求类型
     */
    private List<RequestPath> builderRequestPaths(RequestMappingInfo requestMappingInfo) {
        // 请求路径
        List<String> paths = Optional.ofNullable(requestMappingInfo.getPathPatternsCondition())
            .map(PathPatternsRequestCondition::getPatternValues)
            .map(Lists::newArrayList)
            .orElse(new ArrayList<>(1));
        if (CollectionUtils.isEmpty(paths)) {
            return null;
        }
        // 请求类型
        List<String> requestMethods = requestMappingInfo.getMethodsCondition()
            .getMethods()
            .stream()
            .map(Enum::name)
            .collect(Collectors.toList());
        return paths.stream()
            .map(path -> this.builderRequestPaths(path, requestMethods))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    /**
     * 获取请求路径
     */
    private List<RequestPath> builderRequestPaths(String path, List<String> requestMethods) {
        return requestMethods.stream()
            .map(requestMethod -> new RequestPath().setPath(path).setRequestType(requestMethod))
            .collect(Collectors.toList());
    }

    /**
     * 请求方法描述
     */
    private String getSummary(Method method) {
        // todo
        Operation annotation = null;
//                AnnotationUtil.getAnnotation(method, Operation.class);
        String summary = Optional.ofNullable(annotation).map(Operation::summary).orElse(null);
        return StringUtils.isNotBlank(summary) ? summary : method.getName();
    }

    /**
     * 请求controller描述
     */
    private String getTagName(Class<?> beanClass) {
        Tag annotation = null;
//                AnnotationUtil.getAnnotation(method, Operation.class);
        return Optional.ofNullable(annotation).map(Tag::name).orElse(beanClass.getSimpleName());
    }

}
