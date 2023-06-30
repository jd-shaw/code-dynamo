package com.shaw.iam.core.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaw.iam.core.user.entity.UserDept;

/**
 * 用户部门关系
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface UserDeptDao extends JpaRepository<UserDept, String> {

	void save(List<UserDept> userDepots);

	void deleteByUserId(String userId);

	void deleteByUserIdIn(List<String> userIds);

	List<UserDept> findByUserId(String userId);
}
