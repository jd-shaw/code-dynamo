package com.shaw.iam.core.permission.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.permission.entity.PermPath;

/**
 * 权限
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface PermPathDao extends JpaRepositoryImplementation<PermPath, String> {

}
