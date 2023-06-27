package com.shaw.iam.core.permission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.permission.entity.PermMenu;

/**
 * 权限配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface PermMenuDao extends JpaRepository<PermMenu, String> {

}
