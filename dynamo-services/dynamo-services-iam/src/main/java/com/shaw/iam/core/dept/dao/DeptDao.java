package com.shaw.iam.core.dept.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.dept.entity.Dept;

/**
 * 部门
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface DeptDao extends JpaRepository<Dept, String> {

	void deleteByOrgCode(String orgCode);

	Optional<Dept> findFirstByParentIdIsNullOrderByOrgCodeDesc();

	Optional<Dept> findFirstByParentIdOrderByOrgCategoryDesc(String parentId);

}
