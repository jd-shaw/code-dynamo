package com.shaw.iam.core.permission.service;

import java.util.List;

import com.shaw.iam.dto.permission.PermMenuDto;

/**
 * 权限
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface PermMenuService {

	List<PermMenuDto> findListByClientCode(String clientCode);

	List<PermMenuDto> findListByIds(List<String> ids);

}
