package com.shaw.sys.core.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.sys.core.entity.AppVersion;

/**
 * app版本管理
 *
 * @author shaw
 * @date 2023/06/28
 */
@Repository
public interface AppVersionDao extends JpaRepositoryImplementation<AppVersion, String> {

}
