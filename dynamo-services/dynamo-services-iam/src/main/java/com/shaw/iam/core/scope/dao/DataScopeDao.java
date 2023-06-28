package com.shaw.iam.core.scope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.scope.entity.DataScope;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface DataScopeDao extends JpaRepository<DataScope, String> {

	public boolean existsByCode(String code);

	public boolean existsByCodeAndIdNot(String code, String id);

	public boolean existsByName(String name);

	public boolean existsByNameAndIdNot(String name, String id);
}
