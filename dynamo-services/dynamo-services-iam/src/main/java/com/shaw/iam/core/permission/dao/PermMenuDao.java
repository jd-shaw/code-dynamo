package com.shaw.iam.core.permission.dao;

import java.util.List;

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

	List<PermMenu> findListByClientCode(String clientCode);

	boolean existsByPermCode(String permCode);

	boolean existsByPermCodeAndId(String permCode, String id);

	boolean existsByParentId(String parentId);

	List<PermMenu> findByParentId(String parentId);

}
