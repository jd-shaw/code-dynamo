package com.shaw.iam.core.upms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.upms.entity.UserRole;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface UserRoleDao extends JpaRepository<UserRole, String> {

	List<UserRole> findListByUserId(String userId);

	boolean existsByRoleId(String roleId);
}
