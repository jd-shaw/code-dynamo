package com.shaw.iam.core.dept.service;

import org.springframework.stereotype.Service;

import com.shaw.iam.core.dept.dao.DeptDao;

import lombok.RequiredArgsConstructor;

/**
 * 部门规则工具类
 *
 * @author shaw
 * @date 2023/06/20
 */
@Service
@RequiredArgsConstructor
public class DeptRuleService {

	private final DeptDao deptDao;

}
