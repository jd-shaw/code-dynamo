package com.shaw.quartz.service.impl;

import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.quartz.dao.QuartzJobLogDao;
import com.shaw.quartz.dto.QuartzJobLogDto;
import com.shaw.quartz.entity.QuartzJobLog;
import com.shaw.quartz.param.QuartzJobLogQuery;
import com.shaw.quartz.service.QuartzJobLogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjd
 * @date 2023/7/5
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class QuartzJobLogServiceImpl implements QuartzJobLogService {

    private final QuartzJobLogDao quartzJobLogDao;

    /**
     * 添加
     */
    @Override
    @Async("asyncExecutor")
    public void add(QuartzJobLog quartzJobLog) {
        quartzJobLog.setCreateTime(LocalDateTime.now());
        quartzJobLogDao.save(quartzJobLog);
    }

    /**
     * 分页
     */
    @Override
    public PageResult<QuartzJobLogDto> page(PageParam pageParam, QuartzJobLogQuery query) {
        Specification<QuartzJobLog> specification = new Specification<QuartzJobLog>() {
            @Override
            public Predicate toPredicate(Root<QuartzJobLog> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.hasLength(query.getClassName())) {
                    predicateList
                            .add(criteriaBuilder.like(root.get("handlerName").as(String.class), "%" + query.getClassName() + "%"));
                }
                if (query.getSuccess() != null) {
                    predicateList
                            .add(criteriaBuilder.equal(root.get("success").as(Boolean.class), query.getSuccess()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
        Page<QuartzJobLog> page = getQuartzJobLogDao().findAll(specification, pageable);
        return new PageResult<QuartzJobLogDto>().setSize(page.getSize()).setCurrent(page.getNumber())
                .setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
    }

    /**
     * 单条
     */
    @Override
    public QuartzJobLogDto findById(String id) {
        return getQuartzJobLogDao().findById(id).map(QuartzJobLog::toDto).orElseThrow(DataNotExistException::new);
    }
}
