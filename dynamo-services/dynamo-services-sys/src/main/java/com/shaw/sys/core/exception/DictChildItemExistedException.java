package com.shaw.sys.core.exception;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;
import com.shaw.sys.core.code.BspErrorCodes;

/**
 * @author shaw
 * @date 2023/06/28
 */
public class DictChildItemExistedException extends BaseException implements Serializable {

	private static final long serialVersionUID = -3964173905076738575L;

	public DictChildItemExistedException() {
		super(BspErrorCodes.CHILD_ITEM_EXISTED, "存在字典子项，您无法将其删除。");
	}

}
