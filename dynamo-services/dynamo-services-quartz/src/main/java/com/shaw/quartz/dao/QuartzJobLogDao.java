package com.shaw.quartz.dao;

import com.shaw.quartz.entity.QuartzJobLog;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Repository
public interface QuartzJobLogDao extends JpaRepositoryImplementation<QuartzJobLog, String> {

}
