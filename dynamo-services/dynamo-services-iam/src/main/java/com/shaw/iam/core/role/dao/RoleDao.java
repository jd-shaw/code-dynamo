package com.shaw.iam.core.role.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.role.entity.Role;

/**
 * 角色
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface RoleDao extends JpaRepositoryImplementation<Role, String> {

	boolean existsByCode(String code);

	boolean existsByCodeAndIdNot(String code, String id);

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, String id);

}
