package com.shaw.iam.core.client.service;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.dto.client.LoginTypeDto;
import com.shaw.iam.param.client.LoginTypeParam;

import java.util.List;

/**
 * 终端
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface LoginTypeService {

    PageResult<LoginTypeDto> page(PageParam pageParam, LoginTypeParam param);

    LoginTypeDto add(LoginTypeParam param);

    LoginTypeDto update(LoginTypeParam param);

    LoginTypeDto findById(String id);

    LoginTypeDto findByCode(String code);

    List<LoginTypeDto> findAll();

    void delete(String id);

    boolean existsByCode(String code);

    boolean existsByCode(String code, String id);
}
