package com.shaw.sys.core.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.sys.core.entity.SysKeyValue;

/**
 * @author shaw
 * @date 2023/06/28
 */
@Repository
public interface SysKeyValueDao extends JpaRepositoryImplementation<SysKeyValue, String> {

	Optional<SysKeyValue> findByKey(String key);

	List<SysKeyValue> findByKeyIn(List<String> key);

}
