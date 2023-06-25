package com.shaw.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类.
 * <p>
 * 提供调用getter/setter方法, 访问私有变量, 调用私有方法, 获取泛型类型Class, 被AOP过的真实类等工具函数.
 */
@Slf4j
@SuppressWarnings({"rawtypes"})
public abstract class ReflectionUtils {

    public static final String CGLIB_CLASS_SEPARATOR = "$$";

    /**
     * 调用Getter方法.
     */
    public static Object invokeGetterMethod(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, new Class[]{}, new Object[]{});
    }

    /**
     * 调用Setter方法.使用value的Class来查找Setter方法.
     */
    public static void invokeSetterMethod(Object obj, String propertyName, Object value) {
        invokeSetterMethod(obj, propertyName, value, null);
    }

    /**
     * 调用Setter方法.
     *
     * @param propertyType 用于查找Setter方法,为空时使用value的Class替代.
     */
    public static void invokeSetterMethod(Object obj, String propertyName, Object value, Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class[]{type}, new Object[]{value});
    }

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     */
    public static Object getFieldValue(final Object obj, final String fieldName) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }

        Object result = null;
        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            log.error("不可能抛出的异常{}", e.getMessage());
        }
        return result;
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     */
    public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            if (log.isWarnEnabled())
                log.warn("Could not find field [" + fieldName + "] on target [" + obj + "]");

            return;
        }

        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            log.error("不可能抛出的异常:{}", e.getMessage());
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
     * <p>
     * <p>
     * 如向上转型到Object仍无法找到, 返回null.
     */
    public static Field getAccessibleField(final Object obj, final String fieldName) {
        AssertUtils.notNull(obj, "object不能为空");
        AssertUtils.hasText(fieldName, "fieldName");
        Field field = org.springframework.util.ReflectionUtils.findField(obj.getClass(), fieldName);
        if (field != null) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符. 用于一次性调用的情况.
     */
    public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
                                      final Object[] args) {
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
        }

        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
     * <p>
     * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
     * args)
     */
    public static Method getAccessibleMethod(final Object obj, final String methodName,
                                             final Class<?>... parameterTypes) {
        AssertUtils.notNull(obj, "object不能为空");
        Method method = org.springframework.util.ReflectionUtils.findMethod(obj.getClass(), methodName, parameterTypes);
        if (method != null) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException("Reflection Exception.", e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }

    /**
     * 按Filed的类型取得Field列表
     */
    public static List<Field> getFieldsByType(Object object, Class type) {
        ArrayList<Field> list = new ArrayList<Field>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(type)) {
                list.add(field);
            }
        }
        return list;
    }

    /**
     * 获取代理类的目标Class
     */
    public static Class<?> getTargetClass(Object proxyInstance) {
        Validate.notNull(proxyInstance, "Instance must not be null");
        Class clazz = proxyInstance.getClass();
        if ((clazz != null) && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            Class<?> superClass = clazz.getSuperclass();
            if ((superClass != null) && !Object.class.equals(superClass)) {
                return superClass;
            }
        }
        return clazz;
    }

    /**
     * 对于被cglib AOP过的对象, 取得目标Class类型.
     */
    public static Class<?> getTargetClass(Class<?> proxyClazz) {
        if (proxyClazz != null && proxyClazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            Class<?> superClass = proxyClazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass)) {
                return superClass;
            }
        }
        return proxyClazz;
    }

    public static String getFieldNameByJavaBeanMethod(Method method) {
        String methodName = method.getName();
        if (StringUtils.startsWith(methodName, "get") || StringUtils.startsWith(methodName, "set")) {
            return StringUtils.lowerCase(String.valueOf(methodName.charAt(3))) + methodName.substring(4);
        } else if (StringUtils.startsWith(methodName, "is")) {
            return StringUtils.lowerCase(String.valueOf(methodName.charAt(2))) + methodName.substring(3);
        }
        return methodName;
    }

    public static List<Field> getAllDeclaredFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<Field>();

        Class<?> targetClass = clazz;
        while (targetClass != Object.class) {
            Field[] fs = targetClass.getDeclaredFields();
            fields.addAll(Arrays.asList(fs));
            targetClass = targetClass.getSuperclass();
        }
        return fields;
    }
}
