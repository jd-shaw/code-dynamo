package com.shaw.iam.core.scope.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.scope.entity.DataScopeUser;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface DataScopeUserDao extends JpaRepository<DataScopeUser, String> {

	public void deleteByDataScopeId(String dataScopeId);

	public List<DataScopeUser> findByDataScopeId(String dataScopeId);
}
