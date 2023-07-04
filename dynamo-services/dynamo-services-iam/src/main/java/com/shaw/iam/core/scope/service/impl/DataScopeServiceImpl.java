package com.shaw.iam.core.scope.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.core.dept.event.DeptDeleteEvent;
import com.shaw.iam.core.scope.dao.DataScopeDao;
import com.shaw.iam.core.scope.entity.DataScope;
import com.shaw.iam.core.scope.entity.DataScopeDept;
import com.shaw.iam.core.scope.service.DataScopeDeptService;
import com.shaw.iam.core.scope.service.DataScopeService;
import com.shaw.iam.core.scope.service.DataScopeUserService;
import com.shaw.iam.core.upms.service.UserDataScopeService;
import com.shaw.iam.dto.scope.DataScopeDeptDto;
import com.shaw.iam.dto.scope.DataScopeDto;
import com.shaw.iam.param.scope.DataScopeDeptParam;
import com.shaw.iam.param.scope.DataScopeParam;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.bean.BeanUtilsBean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/6/28
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeServiceImpl implements DataScopeService {

	private final DataScopeDao dataScopeDao;

	private final DataScopeUserService dataScopeUserService;
	private final DataScopeDeptService dataScopeDeptService;
	private final UserDataScopeService userDataScopeService;

	/**
	 * 添加数据范围权限
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(DataScopeParam param) {
		DataScope dataScope = DataScope.init(param);
		dataScope.setId(RandomUIDUtils.getUID());
		dataScope.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
		getDataScopeDao().save(dataScope);
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(DataScopeParam param) {
		DataScope dataScope = getDataScopeDao().findById(param.getId()).orElseThrow(() -> new BaseException("数据不存在"));
		BeanUtils.copyProperties(param, dataScope, BeanUtilsBean.getNullPropertyNames(param));
		dataScope.setType(null);
		getDataScopeDao().save(dataScope);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		if (!getDataScopeDao().existsById(id)) {
			throw new BaseException("数据不存在");
		}
		if (getUserDataScopeService().existsByDataScopeId(id)) {
			throw new BaseException("该权限已经有用户在使用，无法删除");
		}
		getDataScopeDao().deleteById(id);
		getDataScopeUserService().deleteByDataScopeId(id);
		getUserDataScopeService().deleteByDataScopeId(id);
	}

	/**
	 * 添加部门关联范围权限关系
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = { CachingCode.USER_DATA_SCOPE }, allEntries = true)
	public void saveDeptAssign(DataScopeDeptParam param) {
		DataScope dataScope = getDataScopeDao().findById(param.getDataScopeId())
				.orElseThrow(DataNotExistException::new);
		//		val scope = CollUtil.newArrayList(DataScopeEnum.DEPT_SCOPE.getCode(),
		//				DataScopeEnum.DEPT_AND_USER_SCOPE.getCode());
		//		if (!scope.contains(dataScope.getType())) {
		//			throw new BizException("非法操作");
		//		}

		// 先删后增
		List<DataScopeDeptDto> dateScopedDeptList = getDataScopeDeptService().findByDateScopeId(param.getDataScopeId());
		List<String> deptIdsByDb = dateScopedDeptList.stream().map(DataScopeDeptDto::getDeptId)
				.collect(Collectors.toList());

		// 要删除的
		List<String> deptIds = param.getDeptIds();
		List<String> deleteIds = dateScopedDeptList.stream()
				.filter(dataScopeDept -> !deptIds.contains(dataScopeDept.getDeptId())).map(DataScopeDeptDto::getId)
				.collect(Collectors.toList());
		// 要增加的
		List<DataScopeDept> dataScopeDepths = deptIds.stream().filter(id -> !deptIdsByDb.contains(id))
				.map(deptId -> new DataScopeDept(param.getDataScopeId(), deptId)).collect(Collectors.toList());
		getDataScopeDeptService().deleteByIds(deleteIds);
		getDataScopeDeptService().save(dataScopeDepths);
	}

	/**
	 * 处理部门被删除的情况
	 */
	@EventListener
	public void DeptDeleteEventListener(DeptDeleteEvent event) {
		getDataScopeDeptService().deleteByDeptIds(event.getDeptIds());
	}

	/**
	 * 获取关联的部门id集合
	 */
	@Override
	public List<String> findDeptIds(String id) {
		return getDataScopeDeptService().findByDateScopeId(id).stream().map(DataScopeDeptDto::getDeptId)
				.collect(Collectors.toList());
	}

	/**
	 * 判断权限编码是否存在
	 */
	@Override
	public boolean existsByCode(String code) {
		return getDataScopeDao().existsByCode(code);
	}

	/**
	 * 判断权限编码是否存在
	 */
	@Override
	public boolean existsByCodeAndIdNot(String code, String id) {
		return getDataScopeDao().existsByCodeAndIdNot(code, id);
	}

	/**
	 * name是否存在
	 */
	@Override
	public boolean existsByName(String name) {
		return getDataScopeDao().existsByName(name);
	}

	/**
	 * name是否存在
	 */
	@Override
	public boolean existsByNameAndIdNot(String name, String id) {
		return getDataScopeDao().existsByNameAndIdNot(name, id);
	}

	/**
	 * 获取单条
	 */
	@Override
	public DataScopeDto findById(String id) {
		return getDataScopeDao().findById(id).map(DataScope::toDto).orElseThrow(() -> new BaseException("数据不存在"));
	}

	/**
	 * 查询用户所对应的数据权限信息
	 */
	@Override
	public DataScopeDto findDataScopeByUserId(String userId) {
		String dataScopeIdByUser = getUserDataScopeService().findDataScopeIdByUserId(userId);
		if (org.apache.commons.lang3.StringUtils.isBlank(dataScopeIdByUser)) {
			return new DataScopeDto();
		}
		return findById(dataScopeIdByUser);
	}

	/**
	 * 列表查询
	 */
	@Override
	public List<DataScopeDto> findAll() {
		return ResultConvertUtil.dtoListConvert(getDataScopeDao().findAll());
	}

	@Override
	public PageResult<DataScopeDto> page(PageParam pageParam, DataScopeParam param) {
		Specification<DataScope> specification = new Specification<DataScope>() {
			@Override
			public Predicate toPredicate(Root<DataScope> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.hasLength(param.getCode())) {
					predicateList
							.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + param.getCode() + "%"));
				}
				if (StringUtils.hasLength(param.getName())) {
					predicateList
							.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + param.getName() + "%"));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
		Page<DataScope> page = getDataScopeDao().findAll(specification, pageable);
		return new PageResult<DataScopeDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}
}
