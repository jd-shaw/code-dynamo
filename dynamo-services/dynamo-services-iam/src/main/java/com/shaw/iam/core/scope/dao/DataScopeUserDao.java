package com.shaw.iam.core.scope.dao;

import com.shaw.iam.core.scope.entity.DataScopeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface DataScopeUserDao extends JpaRepository<DataScopeUser, String> {

    @Modifying
    @Query("DELETE FROM DataScopeUser u WHERE u.dataScope.id = :dataScopeId")
    void deleteByDataScopeId(@Param("dataScopeId") String dataScopeId);

    @Query("SELECT u FROM DataScopeUser u WHERE u.dataScope.id = :dataScopeId")
    List<DataScopeUser> findByDataScopeId(String dataScopeId);

}
