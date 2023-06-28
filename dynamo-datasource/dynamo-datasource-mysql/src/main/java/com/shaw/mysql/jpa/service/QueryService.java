package com.shaw.mysql.jpa.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface QueryService<T, ID extends Serializable> {
	/**
	 * 根据ID获得单个对象
	 *
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	T get(ID id);

	/**
	 * 获得对象所有集合
	 *
	 * @return
	 * @throws DataAccessException
	 */
	List<T> getAll();

	boolean exists(ID id);

}
