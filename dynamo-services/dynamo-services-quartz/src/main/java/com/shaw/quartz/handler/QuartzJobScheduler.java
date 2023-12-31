package com.shaw.quartz.handler;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import com.shaw.commons.exception.BaseException;
import com.shaw.utils.RandomUIDUtils;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务调度器
 *
 * @author shaw
 * @date 2023/7/5
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QuartzJobScheduler {

	private final Scheduler scheduler;

	/**
	 * 立即执行的任务分组
	 */
	private static final String JOB_TEST_GROUP = "job_test_group";

	/**
	 * 参数
	 */
	private static final String PARAMETER = "parameter";

	/**
	 * 添加定时任务
	 *
	 * @param id           主键
	 * @param jobClassName 任务类名
	 * @param cron         表达式
	 * @param parameter    参数
	 */
	public void add(String id, String jobClassName, String cron, String parameter) {
		try {
			// 启动调度器
			scheduler.start();
			// 构建job信息
			JobDetail jobDetail = JobBuilder.newJob(getJobClass(jobClassName)).withIdentity(id)
					.usingJobData(PARAMETER, parameter).build();
			// 表达式调度构建器(即任务执行的时间)
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
			scheduleBuilder.withMisfireHandlingInstructionDoNothing();
			// 按新的cronExpression表达式构建一个新的trigger
			CronTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(id)
					.withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
			throw new BaseException("创建定时任务失败");
		}
	}

	/**
	 * 删除定时任务
	 */
	public void delete(String id) {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(id));
			scheduler.unscheduleJob(TriggerKey.triggerKey(id));
			scheduler.deleteJob(JobKey.jobKey(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BaseException("删除定时任务失败");
		}
	}

	/**
	 * 立即执行
	 *
	 * @param jobClassName 任务类名
	 * @param parameter    参数
	 */
	public void execute(String jobClassName, String parameter) {
		try {
			String identity = jobClassName + RandomUIDUtils.getUID(8);

			// 定义一个Trigger
			SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity(identity, JOB_TEST_GROUP)
					.startNow().build();
			// 构建job信息
			JobDetail jobDetail = JobBuilder.newJob(getJobClass(jobClassName)).withIdentity(identity)
					.usingJobData(PARAMETER, parameter).build();
			// 将trigger和 jobDetail 加入这个调度
			scheduler.scheduleJob(jobDetail, trigger);
			// 启动scheduler
			scheduler.start();
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
			throw new BaseException("定时任务启动失败");
		}
	}

	/**
	 * 获取定时任务列表
	 */
	public List<Trigger> findTriggers() {
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			return jobKeys.stream().map(this::getTriggersOfJob).flatMap(Collection::stream)
					.collect(Collectors.toList());

		} catch (SchedulerException e) {
			throw new BaseException(e.getMessage());
		}
	}

	@SneakyThrows
	private List<? extends Trigger> getTriggersOfJob(JobKey jobKey) {
		return scheduler.getTriggersOfJob(jobKey);
	}

	/**
	 * 获取任务对象
	 */
	public Class<? extends Job> getJobClass(String classname) {
		Class<?> clazz;
		try {
			clazz = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			throw new BaseException("找不到该定时任务类名");
		}
		if (Job.class.isAssignableFrom(clazz)) {
			// noinspection unchecked
			return (Class<Job>) clazz;
		}
		throw new BaseException("该类不是定时任务类");
	}

}
