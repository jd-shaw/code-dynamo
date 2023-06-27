package com.shaw.commons.validation;

/**
 * 校验分组
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface ValidationGroup {

	/**
	 * 参数校验分组：增加
	 */
	@interface add {

	}

	/**
	 * 参数校验分组：编辑
	 */
	@interface edit {

	}

	/**
	 * 参数校验分组：删除
	 */
	@interface delete {

	}

	/**
	 * 参数校验分组：查询
	 */
	@interface query {

	}

}
