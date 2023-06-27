package com.shaw.utils.text;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

/**
 * 正则工具类
 *
 **/
@UtilityClass
public class RegexUtil {

	private static final String EMAIL_REGEX = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

	private static final String PHONE_REGEX = "^\\+[1-9]\\d*|0$";

	/**
	 * 是否是邮箱
	 */
	public static boolean isEmailPattern(String content) {
		return Pattern.matches(EMAIL_REGEX, content);
	}

	/**
	 * 是否是手机号
	 */
	public static boolean isPhonePattern(String content) {
		return Pattern.matches(PHONE_REGEX, content);
	}

}
