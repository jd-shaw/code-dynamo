package com.shaw.sys.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.sys.core.entity.DictionaryItem;

/**
 * 数据字典
 *
 * @author shaw
 * @date 2023/06/28
 */
@Repository
public interface DictionaryItemDao extends JpaRepositoryImplementation<DictionaryItem, String> {

	public boolean existsByDictId(String dictId);

	public boolean existsByCodeAndDictId(String code, String dictId);

	public boolean existsByCodeAndDictIdAndIdNot(String code, String dictId, String id);

	/**
	 * 查询指定字典下的所有内容
	 */
	public List<DictionaryItem> findByDictId(String dictId);

	/**
	 * 查询指定字典下的所有内容
	 */
	public List<DictionaryItem> findByDictCodeAndEnable(String dictCode, boolean enable);

	@Modifying
	@Query("update DictionaryItem u set u.dictCode = ?2 where u.dictId = ?1")
	public void updateDictCodeByDictId(String dictId, String dictCode);

	public List<DictionaryItem> findByEnable(boolean enable);

	public List<DictionaryItem> findByDictCodeAndCodeAndEnable(String dictCode, String code, boolean enable);

}
