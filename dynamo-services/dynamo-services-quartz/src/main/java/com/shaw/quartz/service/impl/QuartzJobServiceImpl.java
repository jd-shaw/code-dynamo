package com.shaw.quartz.service.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shaw.utils.RandomUIDUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.Trigger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.mysql.jpa.entity.BaseDomain;
import com.shaw.quartz.code.QuartzJobCode;
import com.shaw.quartz.dao.QuartzJobDao;
import com.shaw.quartz.dto.QuartzJobDto;
import com.shaw.quartz.entity.QuartzJob;
import com.shaw.quartz.handler.QuartzJobScheduler;
import com.shaw.quartz.param.QuartzJobParam;
import com.shaw.quartz.service.QuartzJobService;
import com.shaw.utils.bean.BeanUtilsBean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/7/5
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class QuartzJobServiceImpl implements QuartzJobService {

	private final QuartzJobDao quartzJobDao;

	private final QuartzJobScheduler quartzJobScheduler;

	/**
	 * 添加
	 */
	@Override
	@Transactional
	public void add(QuartzJobParam param) {
		QuartzJob quartzJob = QuartzJob.init(param);
		quartzJob.setState(QuartzJobCode.STOP);
		quartzJob.setId(RandomUIDUtils.getUID());
		getQuartzJobDao().save(quartzJob);
	}

	/**
	 * 更新
	 */
	@Override
	@Transactional
	public void update(QuartzJobParam param) {
		QuartzJob quartzJob = getQuartzJobDao().findById(param.getId()).orElseThrow(() -> new BaseException("定时任务不存在"));
		BeanUtils.copyProperties(param, quartzJob, BeanUtilsBean.getNullPropertyNames(param));
		getQuartzJobDao().save(quartzJob);
		getQuartzJobScheduler().delete(quartzJob.getId());
		if (Objects.equals(quartzJob.getState(), QuartzJobCode.RUNNING)) {
			getQuartzJobScheduler().add(quartzJob.getId(), quartzJob.getJobClassName(), quartzJob.getCron(),
					quartzJob.getParameter());
		}
	}

	/**
	 * 启动
	 */
	@Override
	@Transactional
	public void start(String id) {
		QuartzJob quartzJob = getQuartzJobDao().findById(id).orElseThrow(() -> new BaseException("定时任务不存在"));
		// 非运行才进行操作
		if (!Objects.equals(quartzJob.getState(), QuartzJobCode.RUNNING)) {
			quartzJob.setState(QuartzJobCode.RUNNING);
			getQuartzJobDao().save(quartzJob);
			getQuartzJobScheduler().add(quartzJob.getId(), quartzJob.getJobClassName(), quartzJob.getCron(),
					quartzJob.getParameter());
		} else {
			throw new BaseException("已经是启动状态");
		}
	}

	/**
	 * 停止
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void stop(String id) {
		QuartzJob quartzJob = getQuartzJobDao().findById(id).orElseThrow(() -> new BaseException("定时任务不存在"));
		if (!Objects.equals(quartzJob.getState(), QuartzJobCode.STOP)) {
			quartzJob.setState(QuartzJobCode.STOP);
			getQuartzJobDao().save(quartzJob);
			getQuartzJobScheduler().delete(id);
		} else {
			throw new BaseException("已经是停止状态");
		}
	}

	/**
	 * 立即执行
	 */
	@Override
	public void execute(String id) {
		QuartzJob quartzJob = getQuartzJobDao().findById(id).orElseThrow(() -> new BaseException("定时任务不存在"));
		getQuartzJobScheduler().execute(quartzJob.getJobClassName(), quartzJob.getParameter());
	}

	/**
	 * 分页
	 */
	@Override
	public PageResult<QuartzJobDto> page(PageParam pageParam, QuartzJobParam param) {
		Specification<QuartzJob> specification = new Specification<QuartzJob>() {
			@Override
			public Predicate toPredicate(Root<QuartzJob> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.hasLength(param.getJobClassName())) {
					predicateList.add(criteriaBuilder.like(root.get("jobClassName").as(String.class),
							"%" + param.getJobClassName() + "%"));
				}
				if (param.getState() != null) {
					predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class), param.getState()));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
		Page<QuartzJob> page = getQuartzJobDao().findAll(specification, pageable);
		return new PageResult<QuartzJobDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	/**
	 * 获取单条
	 */
	@Override
	public QuartzJobDto findById(String id) {
		return getQuartzJobDao().findById(id).map(QuartzJob::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delete(String id) {
		getQuartzJobDao().deleteById(id);
		getQuartzJobScheduler().delete(id);
	}

	/**
	 * 判断是否是定时任务类
	 */
	@Override
	public String judgeJobClass(String jobClassName) {
		try {
			getQuartzJobScheduler().getJobClass(jobClassName);
		} catch (BaseException e) {
			return e.getMessage();
		}
		return null;
	}

	/**
	 * 同步状态
	 */
	@Override
	public void syncJobStatus() {
		Map<String, QuartzJob> quartzJobMap = getQuartzJobDao().findByState(QuartzJobCode.RUNNING).stream()
				.collect(Collectors.toMap(BaseDomain::getId, Function.identity()));
		List<Trigger> triggers = getQuartzJobScheduler().findTriggers();

		// 将开始任务列表里没有的Trigger给删除. 将未启动的任务状态更新成停止
		for (Trigger trigger : triggers) {
			String triggerName = trigger.getKey().getName();
			if (!quartzJobMap.containsKey(triggerName)) {
				getQuartzJobScheduler().delete(triggerName);
			} else {
				quartzJobMap.remove(triggerName);
			}
		}
		// 更新任务列表状态
		Collection<QuartzJob> quartzJobs = quartzJobMap.values();
		for (QuartzJob quartzJob : quartzJobs) {
			quartzJob.setState(QuartzJobCode.STOP);
		}
		if (CollectionUtils.isNotEmpty(quartzJobs)) {
			getQuartzJobDao().saveAll(quartzJobs);
		}
	}
}
