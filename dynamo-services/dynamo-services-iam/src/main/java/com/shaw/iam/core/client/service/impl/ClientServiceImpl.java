package com.shaw.iam.core.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shaw.utils.bean.BeanUtilsBean;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.core.client.dao.ClientDao;
import com.shaw.iam.core.client.entity.Client;
import com.shaw.iam.core.client.service.ClientService;
import com.shaw.iam.dto.client.ClientDto;
import com.shaw.iam.param.client.ClientParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2023/6/28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientDao clientDao;

	/**
	 * 添加
	 */
	@Override
	public void add(ClientParam param) {
		Client client = Client.init(param);
		clientDao.save(client);
	}

	/**
	 * 修改
	 */
	@Override
	public void update(ClientParam param) {
		Client client = clientDao.findById(param.getId()).orElseThrow(DataNotExistException::new);
		BeanUtils.copyProperties(param, client, BeanUtilsBean.getNullPropertyNames(param));
		if (CollectionUtils.isNotEmpty(param.getLoginTypeIdList())) {
			client.setLoginTypeIds(String.join(",", param.getLoginTypeIdList()));
		} else {
			client.setLoginTypeIds("");
		}
		clientDao.save(client);
	}

	@Override
	public PageResult<ClientDto> page(PageParam pageParam, ClientParam clientParam) {
		Specification<Client> specification = new Specification<Client>() {
			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.hasLength(clientParam.getCode())) {
					predicateList.add(
							criteriaBuilder.like(root.get("code").as(String.class), "%" + clientParam.getCode() + "%"));
				}
				if (StringUtils.hasLength(clientParam.getName())) {
					predicateList.add(
							criteriaBuilder.like(root.get("name").as(String.class), "%" + clientParam.getName() + "%"));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
		Page<Client> page = clientDao.findAll(specification, pageable);
		return new PageResult<ClientDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	/**
	 * 获取单条
	 */
	@Override
	public ClientDto findById(String id) {
		return clientDao.findById(id).map(Client::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<ClientDto> findAll() {
		return ResultConvertUtil.dtoListConvert(clientDao.findAll());
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(String id) {
		clientDao.deleteById(id);
	}

	/**
	 * 编码是否已经存在
	 */
	@Override
	public boolean existsByCode(String code) {
		return clientDao.existsByCode(code);
	}

	/**
	 * 编码是否已经存在(不包含自身)
	 */
	@Override
	public boolean existsByCodeAndIdNot(String code, String id) {
		return clientDao.existsByCodeAndIdNot(code, id);
	}

}
