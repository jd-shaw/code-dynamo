package com.shaw.sys.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.sys.core.entity.Dictionary;

/**
 * 字典
 *
 * @author shaw
 * @date 2023/06/28
 */
@Repository
public interface DictionaryDao extends JpaRepositoryImplementation<Dictionary, String> {

	/**
	 * 根据code查询重复
	 */
	public boolean existsByCode(String code);

	/**
	 * 根据code查询重复 排除id
	 */
	public boolean existsByCodeAndIdNot(String code, String id);

	public List<Dictionary> findByEnable(boolean enable);

}
