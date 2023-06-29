package com.shaw.sys.core.exception;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;
import com.shaw.sys.core.code.BspErrorCodes;

/**
 * @author shaw
 * @date 2023/06/28
 */
public class DictAlreadyExistedException extends BaseException implements Serializable {

	public DictAlreadyExistedException() {
		super(BspErrorCodes.DICTIONARY_ALREADY_EXISTED, "字典已经存在.");
	}

}
