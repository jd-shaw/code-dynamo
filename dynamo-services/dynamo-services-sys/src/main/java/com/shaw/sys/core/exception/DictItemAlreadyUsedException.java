package com.shaw.sys.core.exception;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;
import com.shaw.sys.core.code.BspErrorCodes;

/**
 * @author shaw
 * @date 2023/06/28
 */
public class DictItemAlreadyUsedException extends BaseException implements Serializable {

	public DictItemAlreadyUsedException() {
		super(BspErrorCodes.DICTIONARY_ITEM_ALREADY_USED, "词典项目已被使用.");
	}

}
