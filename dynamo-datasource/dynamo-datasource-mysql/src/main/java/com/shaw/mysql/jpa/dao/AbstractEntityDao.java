package com.shaw.mysql.jpa.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.shaw.mysql.jpa.commons.PredicateBuilder;
import com.shaw.mysql.jpa.po.PageQuery;
import com.shaw.mysql.jpa.po.Query;

import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
@Repository
public abstract class AbstractEntityDao<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements EntityDao<T, ID> {

	@PersistenceContext
	private EntityManager em;
	private final JpaEntityInformation<T, ID> entityInformation;

	public AbstractEntityDao(JpaEntityInformation<T, ID> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.entityInformation = entityInformation;
		this.em = em;
	}

	@Override
	public List<T> findAll(Query query) {
		if (query != null) {
			final Query param = query;
			if (query.hasSort()) {
				return this.findAll(
						(Root<T> root, CriteriaQuery<?> cq,
								CriteriaBuilder cb) -> new PredicateBuilder<T>(root, cq, cb, param).builder(),
						getSort(query));
			} else {
				return this.findAll((Root<T> root, CriteriaQuery<?> cq,
						CriteriaBuilder cb) -> new PredicateBuilder<T>(root, cq, cb, param).builder());
			}
		}
		return Collections.emptyList();
	}

	@Override
	public Page<T> findAll(Pageable pageable, PageQuery pageQuery) {
		if (pageQuery != null) {
			final PageQuery param = pageQuery;
			//			Pageable pageable = pageQuery.getPageable();
			//			if (pageQuery.hasSort()) {
			//				pageable = PageRequest.of(pageQuery.getPageable().getPageNumber(),
			//						pageQuery.getPageable().getPageSize(), getSort(pageQuery));
			//			}
			return findAll((Root<T> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) -> new PredicateBuilder<T>(root, cq, cb, param).builder(), pageable);
		}
		return null;
	}

//	@Override
//	public long count(Query query) {
//		final Query param = query;
//		return this.count((Root<T> root, CriteriaQuery<?> cq,
//				CriteriaBuilder cb) -> new PredicateBuilder<T>(root, cq, cb, param).builder());
//	}

	private Sort getSort(Query query) {
		Sort.Direction direction = Sort.Direction.fromOptionalString(query.getDirection()).orElse(Sort.Direction.ASC);
		return Sort.by(direction, query.getSort().split(","));
	}

}
