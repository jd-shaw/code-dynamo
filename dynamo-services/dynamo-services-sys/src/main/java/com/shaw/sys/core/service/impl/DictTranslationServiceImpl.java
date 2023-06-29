package com.shaw.sys.core.service.impl;

import org.springframework.stereotype.Service;

import com.shaw.sys.core.service.DictTranslationService;
import com.shaw.sys.core.service.DictionaryItemService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典值转换
 *
 * @author shaw
 * @date 2022/12/15
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class DictTranslationServiceImpl implements DictTranslationService {

	private final DictionaryItemService dictionaryItemService;

}
