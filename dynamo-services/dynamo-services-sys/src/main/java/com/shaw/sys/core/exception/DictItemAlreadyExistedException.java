package com.shaw.sys.core.exception;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;
import com.shaw.sys.core.code.BspErrorCodes;

/**
 * @author shaw
 * @date 2023/06/28
 */
public class DictItemAlreadyExistedException extends BaseException implements Serializable {

	public DictItemAlreadyExistedException() {
		super(BspErrorCodes.DICTIONARY_ITEM_ALREADY_EXISTED, "字典项目已存在.");
	}

}
