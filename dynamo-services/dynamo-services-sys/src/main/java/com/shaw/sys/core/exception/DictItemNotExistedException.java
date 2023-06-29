package com.shaw.sys.core.exception;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;
import com.shaw.sys.core.code.BspErrorCodes;

/**
 * @author shaw
 * @date 2023/06/28
 */
public class DictItemNotExistedException extends BaseException implements Serializable {

	public DictItemNotExistedException() {
		super(BspErrorCodes.DICTIONARY_ITEM_NOT_EXISTED, "字典项不存在.");
	}

}
