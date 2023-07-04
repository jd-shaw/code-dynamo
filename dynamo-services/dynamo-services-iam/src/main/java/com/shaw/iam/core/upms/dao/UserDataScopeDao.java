package com.shaw.iam.core.upms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.upms.entity.UserDataScope;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface UserDataScopeDao extends JpaRepository<UserDataScope, String> {

	Optional<UserDataScope> findByUserId(String userId);

	boolean existsByDataScopeId(String dataScopeId);

	void deleteByDataScopeId(String dataScopeId);

	void deleteByUserId(String userId);

	void deleteByUserIdIn(List<String> userIds);
}
