package com.shaw.sys.core.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.mysql.jpa.dao.EntityDao;
import com.shaw.sys.core.entity.SystemParameter;

/**
 * 系统参数
 *
 * @author shaw
 * @date 2023/06/28
 */
@Repository
public interface SystemParameterDao extends JpaRepositoryImplementation<SystemParameter, String> {

	/**
	 * 根据键名获取键值
	 */
	public Optional<SystemParameter> findByParamKey(String key);

	/**
	 * key重复检查
	 */
	public boolean existsByParamKey(String paramKey);

	/**
	 * key重复检查
	 */
	public boolean existsByParamKeyAndIdNot(String paramKey, String id);

}
