package com.shaw.quartz.job;

import java.util.concurrent.TimeUnit;

import com.shaw.commons.annotation.JobLog;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/7/17
 */
@Slf4j
@JobLog
//@Component
//@DisallowConcurrentExecution
public class TestJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			//			TimeUnit.SECONDS.sleep(30);
			log.info("定时任务执行了。。。");
			TimeUnit.SECONDS.sleep(3);
			log.info("定时任务执行结束了。。。");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
