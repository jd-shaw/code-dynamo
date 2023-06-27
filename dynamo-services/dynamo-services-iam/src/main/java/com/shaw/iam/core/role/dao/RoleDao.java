package com.shaw.iam.core.role.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.role.entity.Role;

/**
 * 角色
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}
