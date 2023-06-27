package com.shaw.iam.core.scope.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.scope.entity.DataScopeDept;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface DataScopeDeptDao extends JpaRepository<DataScopeDept, String> {

	void deleteByDataScopeId(String dataScopeId);

	/**
	 * 根据部门进行删除
	 */
	//	void deleteByDeptIds(List<String> deptIds);

	List<DataScopeDept> findByDataScopeId(String dataScopeId);

}
