package com.shaw.spring.commons;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ConcurrentReferenceHashMap.ReferenceType;

/**
 * Spring 上下文的工具类
 *
 * @author shaw
 * @date 2023/7/11
 */
public class SpringContextHolder implements ApplicationContextAware {

	private final static Map<String, Object> BEAN_NAME_CACHE = new ConcurrentReferenceHashMap<String, Object>(256,
			ReferenceType.SOFT);
	private final static Map<Class<?>, Object> BEAN_TYPE_CACHE = new ConcurrentReferenceHashMap<Class<?>, Object>(256,
			ReferenceType.SOFT);
	private final static Map<Class<?>, Object> BEANS_TYPE_CACHE = new ConcurrentReferenceHashMap<Class<?>, Object>(256,
			ReferenceType.SOFT);

	private static ApplicationContext applicationContext; // Spring应用上下文环境

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 *
	 * @param applicationContext
	 * @throws BeansException
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (applicationContext == null)
			throw new RuntimeException("applicationContext init failed in class " + this.getClass().getName());

		BEAN_NAME_CACHE.clear();
		BEAN_TYPE_CACHE.clear();
		BEANS_TYPE_CACHE.clear();

		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null)
			throw new RuntimeException("ApplicationContext do not init success");

		return applicationContext;
	}

	/**
	 * 获取对象
	 *
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static <T> T getBean(String name) throws BeansException {
		return getBean(name, true);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name, boolean isCache) throws BeansException {
		if (isCache) {
			T bean = (T) BEAN_NAME_CACHE.get(name);
			if (bean == null) {
				bean = (T) getApplicationContext().getBean(name);
				BEAN_NAME_CACHE.put(name, bean);
			}
			return bean;
		} else {
			return (T) getApplicationContext().getBean(name);
		}
	}

	/**
	 * 获取类型为requiredType的对象
	 *
	 * @param clz
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(Class<T> clz) throws BeansException {
		return getBean(clz, true);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clz, boolean isCache) throws BeansException {
		if (isCache) {
			T bean = (T) BEAN_TYPE_CACHE.get(clz);
			if (bean == null) {
				bean = (T) getApplicationContext().getBean(clz);
				BEAN_TYPE_CACHE.put(clz, bean);
			}
			return bean;
		} else {
			return (T) getApplicationContext().getBean(clz);
		}
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> clz) throws BeansException {
		return getBeansOfType(clz, true);
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> getBeansOfType(Class<T> clz, boolean isCache) throws BeansException {
		if (isCache) {
			Map<String, T> beanMap = (Map<String, T>) BEANS_TYPE_CACHE.get(clz);
			if (beanMap == null) {
				beanMap = getApplicationContext().getBeansOfType(clz);
				BEANS_TYPE_CACHE.put(clz, beanMap);
			}
			return beanMap;
		} else {
			return getApplicationContext().getBeansOfType(clz);
		}
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 *
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return getApplicationContext().containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 *
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return getApplicationContext().isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return getApplicationContext().getType(name);
	}

	/**
	 * 根据bean类型获取注册的bean的名字
	 *
	 * @param type
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getBeanNamesForType(Class<?> type) throws NoSuchBeanDefinitionException {
		return getApplicationContext().getBeanNamesForType(type);
	}

	/**
	 * 根据bean实例获取注册的bean的名字
	 *
	 * @param bean
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String getBeanNameForBean(Object bean) throws NoSuchBeanDefinitionException {
		String[] beanNames = getBeanNamesForType(bean.getClass());
		for (String beanName : beanNames) {
			if (getBean(beanName) == bean) {
				return beanName;
			}
		}
		return null;
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 *
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return getApplicationContext().getAliases(name);
	}

	public static Resource getResource(String location) {
		return getApplicationContext().getResource(location);
	}

}
