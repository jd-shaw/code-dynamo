package com.shaw.iam.core.dept.service.impl;

import java.util.*;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.utils.RandomUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.core.dept.dao.DeptDao;
import com.shaw.iam.core.dept.entity.Dept;
import com.shaw.iam.core.dept.event.DeptDeleteEvent;
import com.shaw.iam.core.dept.service.DeptService;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.dto.dept.DeptTreeResult;
import com.shaw.iam.param.dept.DeptParam;
import com.shaw.utils.bean.BeanUtilsBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/6/29
 */
@Getter
@Slf4j
@Service
@AllArgsConstructor
public class DeptServiceImpl implements DeptService {

	private final DeptDao deptDao;

	private final ApplicationEventPublisher eventPublisher;

	@Override
	public List<DeptDto> findAll() {
		return ResultConvertUtil.dtoListConvert(getDeptDao().findAll());
	}

	@Override
	public List<DeptDto> findByIds(List<String> ids) {
		return ResultConvertUtil.dtoListConvert(getDeptDao().findAllById(ids));
	}

	/**
	 * 添加部门
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_DATA_SCOPE }, allEntries = true)
	public DeptDto add(DeptParam param) {
		Dept dept = Dept.init(param);

		// 先判断该对象有无父级ID,有则意味着不是最高级,否则意味着是最高级
		String parentId = param.getParentId();

		// 部门code生成
		dept.setOrgCode(generateOrgCode(parentId));
		dept.setId(RandomUIDUtils.getUID());
		dept.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
		return deptDao.save(dept).toDto();
	}

	/**
	 * 树状展示
	 */
	@Override
	public List<DeptTreeResult> tree() {
		List<Dept> list = deptDao.findAll();
		return buildTreeList(list);
	}

	/**
	 * 根据id查询
	 */
	@Override
	public DeptDto findById(String id) {
		return deptDao.findById(id).map(Dept::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 编辑数据 编辑部门的部分数据,并保存到数据库
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_DATA_SCOPE }, allEntries = true)
	public DeptDto update(DeptParam param) {
		// 父机构ID 机构编码 不能修改
		Dept dept = deptDao.findById(param.getId()).orElseThrow(() -> new BaseException("机构未找到"));
		param.setParentId(null);
		BeanUtils.copyProperties(param, dept, BeanUtilsBean.getNullPropertyNames(param));
		return deptDao.save(dept).toDto();
	}

	/**
	 * 删除部门
	 */
	@Override
	@CacheEvict(value = { CachingCode.USER_DATA_SCOPE }, allEntries = true)
	@Transactional
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
	@Override
	@Transactional
	@CacheEvict(value = { CachingCode.USER_DATA_SCOPE }, allEntries = true)
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

	/**
	 * 生成机构代码 根机构_子机构_子子机构
	 */
	public String generateOrgCode(String parentId) {
		// 顶级节点机构
		if (Objects.isNull(parentId)) {
			Dept dept = getDeptDao().findFirstByParentIdIsNullOrderByOrgCodeDesc().orElse(null);
			if (Objects.isNull(dept)) {
				return "1";
			} else {
				return this.getNextCode(dept.getOrgCode());
			}
		} else {
			// 父亲
			Dept parenDept = getDeptDao().findById(parentId).orElseThrow(() -> new BaseException("父机构不存在"));
			// 最新的同级节点
			Dept dept = getDeptDao().findFirstByParentIdOrderByOrgCategoryDesc(parenDept.getParentId()).orElse(null);
			if (Objects.isNull(dept)) {
				return parenDept.getOrgCode() + "_1";
			} else {
				return this.getNextCode(dept.getOrgCode());
			}
		}
	}

	/**
	 * 根据前一个code，获取同级下一个code 例如:当前最大code为1_2，下一个code为：1_3
	 */
	public String getNextCode(String code) {
		// 没有分隔符, 纯数字
		if (!StringUtils.contains(code, "_")) {
			int i = Integer.parseInt(code);
			return String.valueOf(i + 1);
		} else {
			int start = code.lastIndexOf("_");
			String sub = StringUtils.substring(code, start + 1, code.length());
			int i = Integer.parseInt(sub);
			// 拼接新的 前缀+ '_'+新编号
			return StringUtils.substring(code, 0, start + 1) + (i + 1);
		}
	}

	/**
	 * 构造部门树状结构
	 */
	public List<DeptTreeResult> buildTreeList(List<Dept> recordList) {
		// 排序
		recordList.sort(Comparator.comparing(Dept::getSortNo));
		List<DeptTreeResult> tree = new ArrayList<>();
		for (Dept dept : recordList) {
			// 查出没有父级的部门
			if (Objects.isNull(dept.getParentId())) {
				DeptTreeResult deptTreeResult = new DeptTreeResult();
				BeanUtils.copyProperties(dept, deptTreeResult);
				this.findChildren(deptTreeResult, recordList);
				tree.add(deptTreeResult);
			}
		}
		return tree;
	}

	/**
	 * 递归查找子节点
	 */
	private void findChildren(DeptTreeResult treeNode, List<Dept> categories) {
		for (Dept category : categories) {
			if (treeNode.getId().equals(category.getParentId())) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				DeptTreeResult childNode = new DeptTreeResult();
				BeanUtils.copyProperties(category, childNode);
				findChildren(childNode, categories);
				// 子节点
				treeNode.getChildren().add(childNode);
			}
		}
	}

}
