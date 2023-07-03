package com.shaw.sys.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shaw.auth.util.SecurityUtil;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.bean.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.sys.core.dao.DictionaryDao;
import com.shaw.sys.core.dao.DictionaryItemDao;
import com.shaw.sys.core.dto.DictionaryDto;
import com.shaw.sys.core.entity.Dictionary;
import com.shaw.sys.core.exception.DictAlreadyExistedException;
import com.shaw.sys.core.exception.DictItemAlreadyUsedException;
import com.shaw.sys.core.exception.DictNotExistedException;
import com.shaw.sys.core.param.DictionaryParam;
import com.shaw.sys.core.service.DictionaryService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaw
 * @date 2020/4/10 15:52
 */
@Slf4j
@Getter
@Service
@AllArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

	private final DictionaryDao dictionaryDao;
	private final DictionaryItemDao dictionaryItemDao;

	/**
	 * 添加字典
	 */
	@Override
	@Transactional
	public DictionaryDto add(DictionaryParam param) {
		if (getDictionaryDao().existsByCode(param.getCode())) {
			throw new DictAlreadyExistedException();
		}
		Dictionary dictionary = Dictionary.init(param);
		dictionary.setId(RandomUIDUtils.getUID());
		dictionary.setCreateBy(SecurityUtil.getUserIdOrDefaultId());
		return getDictionaryDao().save(dictionary).toDto();
	}

	/**
	 * 删除字典
	 */
	@Override
	@Transactional
	public void delete(String id) {
		if (!getDictionaryDao().existsById(id)) {
			throw new DictNotExistedException();
		}
		if (getDictionaryItemDao().existsByDictId(id)) {
			throw new DictItemAlreadyUsedException();
		}
		getDictionaryDao().deleteById(id);
	}

	/**
	 * 更新
	 */
	@Override
	@Transactional
	public DictionaryDto update(DictionaryParam param) {
		Dictionary dictionary = getDictionaryDao().findById(param.getId()).orElseThrow(DictNotExistedException::new);
		// 判断字典是否重名
		if (getDictionaryDao().existsByCodeAndIdNot(param.getCode(), param.getId())) {
			throw new DictAlreadyExistedException();
		}
		// 更新字典项
		BeanUtils.copyProperties(param, dictionary, BeanUtilsBean.getNullPropertyNames(param));
		getDictionaryItemDao().updateDictCodeByDictId(dictionary.getId(), dictionary.getCode());
		return getDictionaryDao().save(dictionary).toDto();
	}

	/**
	 * 判断编码是否存在
	 */
	@Override
	public boolean existsByCode(String code) {
		return getDictionaryDao().existsByCode(code);
	}

	/**
	 * 判断编码是否存在
	 */
	@Override
	public boolean existsByCode(String code, String id) {
		return getDictionaryDao().existsByCodeAndIdNot(code, id);
	}

	/**
	 * 查询指定字典
	 */
	@Override
	public DictionaryDto findById(String id) {
		return getDictionaryDao().findById(id).map(Dictionary::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 查询所有字典
	 */
	@Override
	public List<DictionaryDto> findAll() {
		List<Dictionary> dictionaries = getDictionaryDao().findAll();
		return ResultConvertUtil.dtoListConvert(dictionaries);
	}

	/**
	 * 查询所有字典
	 */
	@Override
	public PageResult<DictionaryDto> page(PageParam pageParam, DictionaryParam clientParam) {
		Specification<Dictionary> specification = new Specification<Dictionary>() {
			@Override
			public Predicate toPredicate(Root<Dictionary> root, CriteriaQuery<?> criteriaQuery,
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
		Page<Dictionary> page = getDictionaryDao().findAll(specification, pageable);
		return new PageResult<DictionaryDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

}
