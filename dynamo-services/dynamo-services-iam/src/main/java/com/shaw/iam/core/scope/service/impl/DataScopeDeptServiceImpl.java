package com.shaw.iam.core.scope.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.core.scope.dao.DataScopeDeptDao;
import com.shaw.iam.core.scope.entity.DataScopeDept;
import com.shaw.iam.core.scope.service.DataScopeDeptService;
import com.shaw.iam.dto.scope.DataScopeDeptDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xjd
 * @date 2023/6/28
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeDeptServiceImpl implements DataScopeDeptService {

	private final DataScopeDeptDao dataScopeDeptDao;

	@Override
	public List<DataScopeDeptDto> findByDateScopeId(String dataScopeId) {
		return ResultConvertUtil.dtoListConvert(getDataScopeDeptDao().findByDataScopeId(dataScopeId));
	}

	@Override
	public void deleteByIds(List<String> ids) {
		getDataScopeDeptDao().deleteAllById(ids);
	}

	@Override
	public void deleteByDeptIds(List<String> deptIds) {
		getDataScopeDeptDao().deleteByDeptIdIn(deptIds);
	}

	@Override
	public void save(List<DataScopeDept> dataScopeDepths) {
		getDataScopeDeptDao().saveAll(dataScopeDepths);
	}
}
