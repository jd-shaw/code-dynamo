package com.shaw.message.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.shaw.message.entity.SiteMessageUser;

/**
 * @author shaw
 * @date 2023/6/30
 */
public interface SiteMessageUserDao extends JpaRepositoryImplementation<SiteMessageUser, String> {

}
