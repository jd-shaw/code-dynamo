package com.shaw.iam.core.dept.service;

import static com.shaw.iam.code.CachingCode.USER_DATA_SCOPE;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.iam.core.dept.dao.DeptDao;
import com.shaw.iam.core.dept.entity.Dept;
import com.shaw.iam.core.dept.event.DeptDeleteEvent;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.dto.dept.DeptTreeResult;
import com.shaw.iam.param.dept.DeptParam;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@AllArgsConstructor
public class DeptService {

	private final DeptDao deptDao;

	private final DeptRuleService deptRuleService;

	private final ApplicationEventPublisher eventPublisher;

	/**
	 * 添加部门
	 */
	@CacheEvict(value = { USER_DATA_SCOPE }, allEntries = true)
	public DeptDto add(DeptParam param) {
		Dept dept = Dept.init(param);

		// 先判断该对象有无父级ID,有则意味着不是最高级,否则意味着是最高级
		String parentId = param.getParentId();

		// 部门code生成
		//		dept.setOrgCode(deptRuleService.generateOrgCode(parentId));

		return deptDao.save(dept).toDto();
	}

	/**
	 * 树状展示
	 */
	//	public List<DeptTreeResult> tree() {
	//		List<Dept> list = deptDao.findAll();
	//		return deptRuleService.buildTreeList(list);
	//	}

	/**
	 * 根据id查询
	 */
	public DeptDto findById(String id) {
		return deptDao.findById(id).map(Dept::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 编辑数据 编辑部门的部分数据,并保存到数据库
	 */
	@CacheEvict(value = { USER_DATA_SCOPE }, allEntries = true)
	public DeptDto update(DeptParam param) {
		// 父机构ID 机构编码 不能修改
		Dept dept = deptDao.findById(param.getId()).orElseThrow(() -> new BaseException("机构未找到"));
		param.setParentId(null);
		BeanUtils.copyProperties(param, dept);
		return deptDao.save(dept).toDto();
	}

	/**
	 * 删除部门
	 */
	@CacheEvict(value = { USER_DATA_SCOPE }, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {

		//		if (String.existsParent(id)) {
		//			throw new BizException("存在子部门,无法直接删除");
		//		}
		deptDao.deleteById(id);
		// 发布部门修改事件
		eventPublisher.publishEvent(new DeptDeleteEvent(this, Collections.singletonList(id)));
	}

	/**
	 * 删除部门及下级部门
	 */
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = { USER_DATA_SCOPE }, allEntries = true)
	public boolean deleteAndChildren(String id) {
		Dept dept = deptDao.findById(id).orElseThrow(DataNotExistException::new);
		if (Objects.isNull(dept)) {
			return false;
		}
		//		deptDao.deleteByOrgCode(dept.getOrgCode());

		// 删除下级部门
		deptDao.deleteById(id);
		return true;
	}

}
