package com.shaw.sys.core.service;

import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.sys.core.dto.AppVersionDto;
import com.shaw.sys.core.dto.DictionaryDto;
import com.shaw.sys.core.param.AppVersionParam;
import com.shaw.sys.core.param.DictionaryParam;

/**
 * 版本管理
 *
 * @author shaw
 * @date 2023/06/28
 */
public interface AppVersionService {

    AppVersionDto add(AppVersionParam param);

    void delete(String id);

    AppVersionDto check();

    AppVersionDto findById(String id);

    PageResult<AppVersionDto> page(PageParam pageParam, DictionaryParam clientParam);

}
