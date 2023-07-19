package com.shaw.mysql.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import com.shaw.mysql.jpa.po.PageQuery;
import com.shaw.mysql.jpa.po.Query;

@NoRepositoryBean
public interface EntityDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	/**
	 * 查询list
	 *
	 */
	List<T> search(@Nullable Query query);

	/**
	 * 查询page
	 *
	 */
	Page<T> search(@Nullable PageQuery pageQuery);

	/**
	 * 查询数量
	 *
	 */
	long countSearch(@Nullable Query query);

}
