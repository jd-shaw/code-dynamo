package com.shaw.iam.core.scope.dao;

import com.shaw.iam.core.scope.entity.DataScopeDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface DataScopeDeptDao extends JpaRepository<DataScopeDept, String> {

    @Modifying
    @Query("DELETE FROM DataScopeDept u WHERE u.dataScope.id = :dataScopeId")
    void deleteByDataScopeId(String dataScopeId);

    @Modifying
    @Query("DELETE FROM DataScopeDept u WHERE u.dataScope.id in (:deptIds)")
    void deleteByDeptIdIn(List<String> deptIds);

    /**
     * 根据部门进行删除
     */
    //	void deleteByDeptIds(List<String> deptIds);
    @Query("SELECT u FROM DataScopeDept u WHERE u.dataScope.id = :dataScopeId")
    List<DataScopeDept> findByDataScopeId(String dataScopeId);

}
