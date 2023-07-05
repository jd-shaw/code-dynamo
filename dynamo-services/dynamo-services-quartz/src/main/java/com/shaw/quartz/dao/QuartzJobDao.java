package com.shaw.quartz.dao;

import com.shaw.quartz.entity.QuartzJob;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Repository
public interface QuartzJobDao extends JpaRepositoryImplementation<QuartzJob, String> {

}
