package com.shaw.mysql.jpa.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

import com.shaw.mysql.jpa.po.Predication;

/**
 * @author shaw
 * @date 2023/7/19
 */
public class SpecificationFactory<T> {
	private static final String POINT_MARK = ".";

	private Specification<T> specs;

	private SpecificationFactory(Specification<T> specs) {
		this.specs = specs;
	}

	public static <T> SpecificationFactory<T> wheres(Specification<T> spec) {
		return new SpecificationFactory<T>(spec);
	}

	public SpecificationFactory<T> and(Specification<T> other) {
		this.specs.and(other);
		return this;
	}

	public SpecificationFactory<T> or(Specification<T> other) {
		this.specs.or(other);
		return this;
	}

	public Specification<T> build() {
		return this.specs;
	}

	/**
	 * 单where条件
	 *
	 */
	public static <T> Specification<T> where(Predication<T> p) {
		List<Predication<T>> ps = new ArrayList<>();
		ps.add(p);
		return where(ps);
	}

	/**
	 * 多where条件and连接
	 *
	 */
	public static <T> Specification<T> where(List<Predication<T>> ps) {
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder
				.and(getPredicateList(root, builder, ps));
	}

	/**
	 * 多where条件or连接
	 *
	 */
	public static <T> Specification<T> or(List<Predication<T>> ps) {
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder
				.or(getPredicateList(root, builder, ps));
	}

	/**
	 * 获取查询条件数组
	 *
	 */
	private static <T> Predicate[] getPredicateList(Root<?> root, CriteriaBuilder builder, List<Predication<T>> ps) {
		List<Predicate> predicateList = new ArrayList<>();
		ps.forEach(p -> {
			Predicate predicate = buildPredicate(builder, root.get(p.getName()), p);
			predicateList.add(predicate);
		});
		return predicateList.toArray(new Predicate[0]);
	}

	/**
	 * 选取查询方式
	 *
	 */
	private static <T> Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Predication<T> p) {
		Predicate predicate;
		switch (p.getOperator()) {
		case LIKE:
			predicate = cb.like(path.as(String.class), p.getValue().toString());
			break;
		case EQ:
			predicate = cb.equal(path, p.getValue());
			break;
		case NOT_EQ:
			predicate = cb.notEqual(path, p.getValue());
			break;
		case GT:
			predicate = cb.greaterThan((Path<Comparable>) path, (Comparable) p.getValue());
			break;
		case GT_EQ:
			predicate = cb.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) p.getValue());
			break;
		case LT:
			predicate = cb.lessThan((Path<Comparable>) path, (Comparable) p.getValue());
			break;
		case LT_EQ:
			predicate = cb.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) p.getValue());
			break;
		case NULL:
			predicate = cb.isNull(path);
			break;
		case NOTNULL:
			predicate = cb.isNotNull(path);
			break;
		case IN:
			predicate = getIn(path, p.getValue());
			break;
		case NOTIN:
			predicate = getIn(path, p.getValue()).not();
			break;
		default:
			throw new IllegalArgumentException("非法的操作符");
		}
		return predicate;
	}

	/**
	 * 创建in操作
	 *
	 */
	private static <T> Predicate getIn(Path<?> path, T value) {
		if (value instanceof Object[]) {
			return path.in((Object[]) value);
		} else if (value instanceof Collection) {
			return path.in((Collection<?>) value);
		} else {
			throw new IllegalArgumentException("非法的IN操作");
		}
	}

	/***********************************************单where条件查询********************************************************/

	// like
	public static <T> Specification<T> like(String name, String value) {
		return (root, query, cb) -> cb.like(root.get(name), value);
	}

	// =
	public static <T> Specification<T> equal(String name, Object value) {
		return (root, query, cb) -> cb.equal(root.get(name), value);
	}

	// !=
	public static <T> Specification<T> notEqual(String name, Object value) {
		return (root, query, cb) -> cb.notEqual(root.get(name), value);
	}

	// >
	public static <T> Specification<T> gt(String name, Object value) {
		return (root, query, cb) -> cb.greaterThan(root.get(name), (Comparable) value);
	}

	// >=
	public static <T> Specification<T> gtEqual(String name, Object value) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(name), (Comparable) value);
	}

	// <
	public static <T> Specification<T> lt(String name, Object value) {
		return (root, query, cb) -> cb.lessThan(root.get(name), (Comparable) value);
	}

	// <=
	public static <T> Specification<T> ltEqual(String name, Object value) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(name), (Comparable) value);
	}

	// is null
	public static <T> Specification<T> isNull(String name) {
		return (root, query, cb) -> cb.isNull(root.get(name));
	}

	// is not null
	public static <T> Specification<T> notNull(String name) {
		return (root, query, cb) -> cb.isNotNull(root.get(name));
	}

	// in
	public static <T> Specification<T> in(String name, Object value) {
		return (root, query, cb) -> root.get(name).in(value);
	}

	// not in
	public static <T> Specification<T> notIn(String name, Object value) {
		return (root, query, cb) -> root.get(name).in(value).not();
	}
}
