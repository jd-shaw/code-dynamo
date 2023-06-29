package com.shaw.sys.core.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shaw.commons.exception.BaseException;
import com.shaw.commons.exception.DataNotExistException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.mysql.jpa.po.BaseDomain;
import com.shaw.sys.core.dao.DictionaryDao;
import com.shaw.sys.core.dao.DictionaryItemDao;
import com.shaw.sys.core.dto.DictionaryItemDto;
import com.shaw.sys.core.dto.DictionaryItemSimpleDto;
import com.shaw.sys.core.entity.Dictionary;
import com.shaw.sys.core.entity.DictionaryItem;
import com.shaw.sys.core.exception.DictItemAlreadyExistedException;
import com.shaw.sys.core.exception.DictItemNotExistedException;
import com.shaw.sys.core.param.DictionaryItemParam;
import com.shaw.sys.core.param.DictionaryParam;
import com.shaw.sys.core.service.DictionaryItemService;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shaw
 * @date 2020/4/16 21:16
 */
@Getter
@Service
@AllArgsConstructor
public class DictionaryItemServiceImpl implements DictionaryItemService {

	private final DictionaryItemDao dictionaryItemDao;
	private final DictionaryDao dictionaryDao;

	/**
	 * 添加内容
	 */
	@Override
	@Transactional
	public DictionaryItemDto add(DictionaryItemParam param) {
		// 在同一个Dictionary不允许存在相同code的DictionaryItem
		if (getDictionaryItemDao().existsByCodeAndDictId(param.getCode(), param.getDictId())) {
			throw new DictItemAlreadyExistedException();
		}
		Dictionary dictionary = getDictionaryDao().findById(param.getDictId())
				.orElseThrow(() -> new BaseException("字典不存在"));
		param.setDictCode(dictionary.getCode());
		DictionaryItem dictionaryItem = DictionaryItem.init(param);
		return getDictionaryItemDao().save(dictionaryItem).toDto();
	}

	/**
	 * 修改内容
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public DictionaryItemDto update(DictionaryItemParam param) {
		// 判断字典item是否存在
		DictionaryItem dictionaryItem = getDictionaryItemDao().findById(param.getId())
				.orElseThrow(DictItemNotExistedException::new);

		// 判断是否有重复code的Item
		if (getDictionaryItemDao().existsByCodeAndDictIdAndIdNot(dictionaryItem.getDictCode(), param.getDictId(),
				param.getId())) {
			throw new DictItemAlreadyExistedException();
		}
		BeanUtils.copyProperties(param, dictionaryItem);
		return getDictionaryItemDao().save(dictionaryItem).toDto();
	}

	/**
	 * 删除内容
	 */
	@Override
	public void delete(String id) {
		getDictionaryItemDao().deleteById(id);
	}

	/**
	 * 根据ID查询指定内容
	 */
	@Override
	public DictionaryItemDto findById(String id) {
		return getDictionaryItemDao().findById(id).map(DictionaryItem::toDto).orElseThrow(DataNotExistException::new);
	}

	/**
	 * 根据字典编码和字典项编码查询启用的菜单项
	 */
	@Override
	public List<DictionaryItem> findEnableByCode(String dictCode, String code) {
		return getDictionaryItemDao().findByDictCodeAndCodeAndEnable(dictCode, code, true);
	}

	/**
	 * 查询指定目录下的所有内容
	 */
	@Override
	public List<DictionaryItemDto> findByDictionaryId(String dictionaryId) {
		return getDictionaryItemDao().findByDictId(dictionaryId).stream()
				.sorted(Comparator.comparingDouble(DictionaryItem::getSortNo)).map(DictionaryItem::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * 查询指定目录下的所有内容
	 */
	@Override
	public List<DictionaryItemDto> findEnableByDictCode(String dictCode) {
		return getDictionaryItemDao().findByDictCodeAndEnable(dictCode, true).stream().map(DictionaryItem::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * 分页查询指定目录下的内容
	 */
	@Override
	public PageResult<DictionaryItemDto> page(PageParam pageParam, DictionaryParam clientParam) {
		Specification<DictionaryItem> specification = new Specification<DictionaryItem>() {
			@Override
			public Predicate toPredicate(Root<DictionaryItem> root, CriteriaQuery<?> criteriaQuery,
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
		Page<DictionaryItem> page = getDictionaryItemDao().findAll(specification, pageable);
		return new PageResult<DictionaryItemDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	/**
	 * 判断编码是否存在
	 */
	@Override
	public boolean existsByCode(String code, String dictId) {
		return getDictionaryItemDao().existsByCodeAndDictId(code, dictId);
	}

	/**
	 * 判断编码是否存在
	 */
	@Override
	public boolean existsByCode(String code, String dictId, String id) {
		return getDictionaryItemDao().existsByCodeAndDictIdAndIdNot(code, dictId, id);
	}

	/**
	 * 获取全部字典项
	 */
	@Override
	public List<DictionaryItemSimpleDto> findAll() {
		return getDictionaryItemDao().findAll().stream()
				.sorted(Comparator.comparing(DictionaryItem::getDictId).thenComparing(DictionaryItem::getSortNo)
						.thenComparing(BaseDomain::getId))
				.map(DictionaryItem::toSimpleDto).collect(Collectors.toList());
	}

	/**
	 * 获取启用的字典项列表
	 */
	@Override
	public List<DictionaryItemSimpleDto> findAllByEnable() {
		// 获取被停用的字典
		List<String> unEnableDictIds = getDictionaryDao().findByEnable(false).stream().map(BaseDomain::getId)
				.collect(Collectors.toList());
		// 过滤掉被停用的字典项
		return getDictionaryItemDao().findByEnable(true).stream().filter(o -> !unEnableDictIds.contains(o.getDictId()))
				.sorted(Comparator.comparing(DictionaryItem::getDictId).thenComparing(DictionaryItem::getSortNo)
						.thenComparing(BaseDomain::getId))
				.map(DictionaryItem::toSimpleDto).collect(Collectors.toList());
	}

}
