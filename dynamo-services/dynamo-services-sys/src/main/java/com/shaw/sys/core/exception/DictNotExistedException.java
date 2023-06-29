package com.shaw.sys.core.exception;

import java.io.Serializable;

import com.shaw.commons.exception.BaseException;
import com.shaw.sys.core.code.BspErrorCodes;

/**
 * @author shaw
 * @date 2023/06/28
 */
public class DictNotExistedException extends BaseException implements Serializable {

	public DictNotExistedException() {
		super(BspErrorCodes.DICTIONARY_NOT_EXISTED, "字典不存在.");
	}

}
