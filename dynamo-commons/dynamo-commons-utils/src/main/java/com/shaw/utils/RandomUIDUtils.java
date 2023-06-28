package com.shaw.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUIDUtils {

	private static final char[] STRPREFIX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] INTPREFIX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	// private static final char[] LETTERPREFIX = { 'a', 'b', 'c', 'd', 'e',
	// 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
	// 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
	// 'C', 'D', 'E', 'F', 'G', 'H', 'I',
	// 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	// 'X', 'Y', 'Z' };

	private static final char[] SUDDIX = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * 根据传入位数，生成数字随机数
	 *
	 * @return length
	 */
	private static String getRandomStr(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * 获取20位的String ID 格式：4位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数+6位随机数
	 *
	 * @return str
	 */
	public static String getUID20() {
		StringBuffer sb = new StringBuffer();
		// 4位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数+6位流水号或6位随机数
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		sb.append(formatter.format(new Date())).append(getRandomStr(6));

		return sb.toString();
	}

	/**
	 * 获取18位的String ID 格式：2位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数+6位随机数
	 *
	 * @return str
	 */
	public static String getUID18() {
		StringBuffer sb = new StringBuffer();
		// 2位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数+6位流水号或6位随机数
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		sb.append(formatter.format(new Date())).append(getRandomStr(6));

		return sb.toString();
	}

	/**
	 * 返回15-16位的long类型主键 格式：2位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数+4位随机数
	 *
	 * @return long
	 */
	public static long getLongUID() {
		StringBuffer sb = new StringBuffer();
		// 2位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数+4位流水号或4位随机数
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		sb.append(formatter.format(new Date())).append(getRandomStr(4));

		if (sb.toString().charAt(0) == '0') {
			return Long.parseLong(sb.toString().substring(1));
		} else {
			return Long.parseLong(sb.toString());
		}
	}

	/**
	 * 获取12+{size}位的String ID 格式：2位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数+{size}位随机数
	 *
	 * @return str
	 */
	public static String getUID(int size) {
		StringBuffer sb = new StringBuffer();
		// 2位年份+2位月份+2位日期+2位小时+2位分钟+2位秒数
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		sb.append(formatter.format(new Date())).append(getRandomStr(size));
		return sb.toString();
	}

	/**
	 * 获取UUID
	 *
	 * @return str
	 */
	public static String getUUID() {
		return java.util.UUID.randomUUID().toString();
	}

	public static String getNumberUID(int size) {
		int count = 0;
		StringBuffer uid = new StringBuffer("");
		Random r = new Random();
		while (count < size) {
			int i = Math.abs(r.nextInt(10));
			if (i >= 0 && i < INTPREFIX.length) {
				uid.append(INTPREFIX[i]);
				count++;
			}
		}
		return uid.toString();
	}

	public static String getStrUID(int size) {
		int count = 0;
		StringBuffer uid = new StringBuffer("");
		Random r = new Random();
		while (count < size) {
			int i = Math.abs(r.nextInt(62));
			if (i >= 0 && i < STRPREFIX.length) {
				uid.append(STRPREFIX[i]);
				count++;
			}
		}
		return uid.toString();
	}

	public static String getUID() {
		char[] chars = String.valueOf(Calendar.getInstance().getTimeInMillis()).toCharArray();
		StringBuffer uid = new StringBuffer(chars.length * 2);
		int count = 0;
		Random r = new Random();
		while (count < chars.length) {
			uid.append(chars[count]);
			int i = r.nextInt(62);
			if (i >= 0 && i < STRPREFIX.length) {
				uid.append(STRPREFIX[i]);
			}
			count++;
		}
		return uid.toString();
	}

	public static int numOfRange(int offset, int end) {
		Random random = new Random();
		int i = (end + 1) - offset;
		if (i <= 0)
			return i;
		int result = random.nextInt(i) + offset;
		return result;
	}

	public static String getSuffix(int size) {
		int count = 0;
		StringBuffer uid = new StringBuffer("");
		Random r = new Random();
		while (count < size) {
			int i = Math.abs(r.nextInt(62));
			if (i >= 0 && i < SUDDIX.length) {
				uid.append(SUDDIX[i]);
				count++;
			}
		}
		return uid.toString();
	}
}
