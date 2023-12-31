package com.shaw.iam.core.client.service;

import java.util.List;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.dto.client.ClientDto;
import com.shaw.iam.param.client.ClientParam;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface ClientService {

	void add(ClientParam param);

	void update(ClientParam param);

	void delete(String id);

	ClientDto findById(String id);

	List<ClientDto> findAll();

	boolean existsByCode(String code);

	boolean existsByCodeAndIdNot(String code, String id);

	PageResult<ClientDto> page(PageParam pageParam, ClientParam clientParam);

}
