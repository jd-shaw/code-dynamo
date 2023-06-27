package com.shaw.iam.core.upms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaw.iam.core.upms.entity.RoleMenu;
import org.springframework.stereotype.Repository;

/**
 * 角色权限关系
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface RoleMenuDao extends JpaRepository<RoleMenu, String> {

}
