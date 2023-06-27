package com.shaw.iam.core.client.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.core.client.dao.ClientDao;
import com.shaw.iam.core.client.entity.Client;
import com.shaw.iam.dto.client.ClientDto;
import com.shaw.iam.param.client.ClientParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientDao clientDao;

	/**
	 * 添加
	 */
	public void add(ClientParam param) {
		Client client = Client.init(param);
		clientDao.save(client);
	}

	/**
	 * 修改
	 */
	public void update(ClientParam param) {
		Client client = clientDao.findById(param.getId()).orElseThrow(DataNotExistException::new);
		BeanUtils.copyProperties(param, client);
		if (CollectionUtils.isNotEmpty(param.getLoginTypeIdList())) {
			client.setLoginTypeIds(String.join(",", param.getLoginTypeIdList()));
		} else {
			client.setLoginTypeIds("");
		}
		clientDao.save(client);
	}

	/**
	 * 分页
	 */
	//    public PageResult<ClientDto> page(PageParam pageParam, ClientParam clientParam) {
	//        return MpUtil.convert2DtoPageResult(clientManager.page(pageParam, clientParam));
	//    }

	/**
	 * 获取单条
	 */
	public ClientDto findById(String id) {
		return clientDao.findById(id).map(Client::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 获取全部
	 */
	public List<ClientDto> findAll() {
		return ResultConvertUtil.dtoListConvert(clientDao.findAll());
	}

	/**
	 * 删除
	 */
	public void delete(String id) {
		clientDao.deleteById(id);
	}

	/**
	 * 编码是否已经存在
	 */
	public boolean existsByCode(String code) {
		return false;
	}

	/**
	 * 编码是否已经存在(不包含自身)
	 */
	public boolean existsByCode(String code, Long id) {
		return false;
	}

}
