package com.shaw.message.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.shaw.message.entity.SiteMessage;

/**
 * @author shaw
 * @date 2023/6/30
 */
public interface SiteMessageDao extends JpaRepositoryImplementation<SiteMessage, String> {

	@Query(value = "select count(*) from site_message", nativeQuery = true)
	int countByReceiveNotRead();

}
