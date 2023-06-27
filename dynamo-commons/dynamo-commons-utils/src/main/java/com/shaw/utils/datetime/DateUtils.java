package com.shaw.utils.datetime;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 *
 * @version 1.0 日期工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static final String DEFAULTDATEPATTERN = "yyyy-MM-dd";
	// 预设日期格式

	// 预设日期时间格式
	public static final String DEFAULTDATETIMEPATTERN = "yyyy-MM-ddHHmmss";

	// 当天开始时间
	public static final String THE_DAY_START_TIME = "yyyy-MM-dd 00:00:00";

	// 当天结束时间
	public static final String THE_DAY_END_TIME = "yyyy-MM-dd 23:59:59";

	// 预设日期时间格式
	public static final String ISO8601LONG = "yyyy-MM-dd'T'HH:mm:ss";

	// 预设日期时间格式
	public static final String YYYYMMDD_T_HHMMSS = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYYMMDD09 = "yyyyMMdd09";

	public static final String YYYYMMDD08 = "yyyyMMdd08";

	public static final String MMDD = "MM-dd";

	public static final String YYYY_MM = "yyyy-MM";

	public static final String YYYY = "yyyy";

	public static final Date MIN_DATE = new Date(0L);

	/**
	 * 精确到时
	 */
	public static final String DEFAULTDATEPATTERNHH = "yyyy-MM-dd HH";

	public static final String DEFAULTDATEPATTERNHHMM = "yyyy-MM-dd HH:mm";

	public static NowTimeFun nowTimeFun = new NowTimeFun() {
		@Override
		public Date getNowTime() {
			return new Date();
		}
	};

	/**
	 * 获得当前时间，格式 yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getCurrDateTime() {
		return format(getNowTime(), DEFAULTDATETIMEPATTERN);
	}

	/**
	 * 获取当天最开始时间
	 *
	 * @return
	 */
	public static String getCurrStartDateTime() {
		return format(getNowTime(), THE_DAY_START_TIME);
	}

	/**
	 * 获取当天最后时间
	 *
	 * @return
	 */
	public static String getCurrEndDateTime() {
		return format(getNowTime(), THE_DAY_END_TIME);
	}

	/**
	 * 根据时间格式获得当前时间
	 */
	public static String getCurrDateTime(String partten) {
		return format(getNowTime(), partten);
	}

	/**
	 * 根据默认格式获得格式化的时间,格式： yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return format(date, DEFAULTDATETIMEPATTERN);
	}

	/**
	 * 根据默认格式获得格式化的时间,格式： yyyy-MM-dd HH:mm:ss
	 *
	 * @param millis
	 * @return
	 */
	public static String formatDateTime(long millis) {
		return format(millis, DEFAULTDATETIMEPATTERN);
	}

	/**
	 * 根据默认格式获得格式化的日期，格式:yyyy/MM/dd
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, DEFAULTDATEPATTERN);
	}

	/**
	 * 根据默认格式获得格式化的日期，格式:yyyy/MM/dd
	 *
	 * @param millis
	 * @return
	 */
	public static String formatDate(long millis) {
		return format(millis, DEFAULTDATEPATTERN);
	}

	/**
	 * 获得格式化的时间
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return format(date.getTime(), pattern);
	}

	/**
	 * 获得格式化的时间
	 *
	 * @param millis
	 * @param pattern
	 * @return
	 */
	public static String format(long millis, String pattern) {
		return DateFormatUtils.format(millis, pattern);
	}

	/**
	 * 根据string类型的日期，添加天数
	 *
	 * @param dateStr
	 * @param pattern
	 * @param amount
	 * @return
	 * @throws ParseException
	 */
	public static String addDays(String dateStr, String pattern, int amount) throws ParseException {
		return format(org.apache.commons.lang3.time.DateUtils.addDays(parse(dateStr, pattern), amount), pattern);
	}

	/**
	 * 根据日期，添加天数
	 *
	 * @param dateStr
	 * @param pattern
	 * @param amount
	 * @return
	 * @throws ParseException
	 */
	public static String addDays(Date date, String pattern, int amount) {
		return format(org.apache.commons.lang3.time.DateUtils.addDays(date, amount), pattern);
	}

	public static String addMonths(String dateStr, String pattern, int amount) throws ParseException {
		return format(org.apache.commons.lang3.time.DateUtils.addMonths(parse(dateStr, pattern), amount), pattern);
	}

	/**
	 * 获得两个时间的间隔,可以按秒、分钟、小时、天来获取
	 *
	 * @param date1
	 *            大的日期在前
	 * @param date2
	 * @return
	 */
	public static int elapsed(Date date1, Date date2, int field) {
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException("The date must not be null");

		long elapsed = date1.getTime() - date2.getTime();
		switch (field) {
		case Calendar.SECOND:
			return ((Number) (elapsed / 1000f)).intValue();
		case Calendar.MINUTE:
			return ((Number) (elapsed / (1000f * 60f))).intValue();
		case Calendar.HOUR:
			return ((Number) (elapsed / (1000f * 60f * 60f))).intValue();
		case Calendar.DATE:
			return ((Number) (elapsed / (1000f * 60f * 60f * 24f))).intValue();
		case Calendar.MONTH:
			return ((Number) (elapsed / (1000f * 60f * 60f * 24f * 30f))).intValue();
		case Calendar.YEAR:
			return ((Number) (elapsed / (1000f * 60f * 60f * 24f * 30f * 12f))).intValue();
		}

		return ((Number) (elapsed / (1000 * 60 * 60 * 24))).intValue();
	}

	/**
	 * 获得两个时间的间隔,可以按秒、分钟、小时、天来获取
	 *
	 * @param date1
	 *            大的日期在前
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int elapsed(String strDate1, String strDate2, String pattern, int field) throws ParseException {
		return elapsed(parse(strDate1, pattern), parse(strDate2, pattern), field);
	}

	/**
	 * 使用参数Format将字符串转为Date
	 *
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
	}

	public static Date parse(String strDate, String pattern, Locale locale) throws ParseException {
		return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(pattern, locale).parse(strDate);
	}

	/**
	 * 按照默认格式（YYYY/MM/DD）将字符串转为Date
	 *
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String strDate) throws ParseException {
		return parse(strDate, DEFAULTDATEPATTERN);
	}

	/**
	 * 按照默认格式（yyyy-MM-dd HH:mm:ss）将字符串转为Date
	 *
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateTime(String strDate) throws ParseException {
		return parse(strDate, YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取传入月份的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取传入月份的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getMaxDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取传入星期的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取传入星期的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getMaxDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		calendar.add(Calendar.DATE, -1);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取传入星期的第一天，周一为每周第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getChinaMinDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取传入星期的最后一天，周一为每周第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getChinaMaxDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.add(Calendar.DATE, -1);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取两个日期的间隔天数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDaysBetween(Date startDate, Date endDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取月的第一天
	 *
	 * @return
	 */
	public static String getMonthFirstDay(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat(pattern).format(cal.getTime());
	}

	/**
	 * 获取月的最后一天
	 *
	 * @return
	 */
	public static String getMonthLastDay(String pattern) {
		return new SimpleDateFormat(pattern).format(getMaxDateOfMonth(getNowTime()));
	}

	public static boolean isSameDay(String dateStr1, String dateStr2, String pattern) throws ParseException {
		return org.apache.commons.lang3.time.DateUtils.isSameDay(parse(dateStr1, pattern), parse(dateStr2, pattern));
	}

	public static boolean isSameTime(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameTime(cal1, cal2);
	}

	public static boolean isSameTime(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
				&& cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)
				&& cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE)
				&& cal1.get(Calendar.SECOND) == cal2.get(Calendar.SECOND));
	}

	public static Date formatDateToData(Date date) {
		return formatDateToData(date, DEFAULTDATEPATTERN);
	}

	public static Date formatDateToData(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateStr = sdf.format(date);
		Date result = null;
		try {
			result = sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return result;
	}

	/**
	 *
	 * 获取当前时间的23:59:59 @param date @return Date @throws
	 */
	public static Date getDateLastTime(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 *
	 * 获取当前时间的 12:00:00 @param date @return Date @throws
	 */
	public static Date getDateCentre2Time(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 12);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 *
	 * 获取当前时间的 11:59:59 @param date @return Date @throws
	 */
	public static Date getDateCentreTime(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 *
	 * 获取当前时间的 00:00:00 @param date @return Date @throws
	 */
	public static Date getDateFirstTime(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 获取当前时间的 00：xx:00
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinDate(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int minute = calendar.get(Calendar.MINUTE);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		return null;
	}

	public static Date getHourDate(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 判断source日期是否在beginTime(含)和endTime(含)之间
	 *
	 * @param source
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean isRange(Date source, Date beginTime, Date endTime) {
		return source != null && beginTime.before(ceiling(source, Calendar.SECOND))
				&& ceiling(endTime, Calendar.SECOND).after(source);
	}

	/**
	 * yyyy-MM
	 *
	 * @param date
	 * @return
	 */
	public static String firstDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String year = date.split("-")[0];
		String month = date.split("-")[1];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(cal.getTime());
	}

	public static String lastDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String year = date.split("-")[0];
		String month = date.split("-")[1];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return format.format(cal.getTime());
	}

	public static List<Date> getDates(Date beginDate, Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		GregorianCalendar calBegin = new GregorianCalendar();
		calBegin.setTime(beginDate);
		calBegin.set(Calendar.HOUR_OF_DAY, 0);
		calBegin.set(Calendar.MINUTE, 0);
		calBegin.set(Calendar.SECOND, 0);
		calBegin.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTime(endDate);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);
		while (calBegin.compareTo(calEnd) == 0 || calEnd.after(calBegin)) {
			dates.add(calBegin.getTime());
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dates;
	}

	public static String getEDate(Date date) {
		if (date != null) {
			String dateStr = DateUtils.format(date, DateUtils.DEFAULTDATEPATTERN);
			ParsePosition pos = new ParsePosition(0);
			SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.DEFAULTDATEPATTERN);
			date = formatter.parse(dateStr, pos);
			String j = date.toString();
			String[] k = j.split(" ");
			return k[2] + "th " + k[1].toUpperCase() + " " + k[5].substring(0, 4);
		}
		return "";
	}

	// 获取某年某月的第一天日期
	public static Date getStartMonthDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	// 获取某年某月的最后一天日期
	public static Date getEndMonthDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(year, month - 1, day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取输入日期当天最早时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinDateOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 两天之间的所有日期
	 *
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<String> rangeTwoDay(String startDay, String endDay) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<String> dates = new ArrayList<String>();
		try {
			Date datetmp = format.parse(startDay);
			long end = format.parse(endDay).getTime();
			while (datetmp.getTime() < end) {
				dates.add(format.format(datetmp));
				datetmp.setDate(datetmp.getDate() + 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}

	public static Date getNowTime() {
		return nowTimeFun.getNowTime();
	}

	public static void setNowTimeFun(NowTimeFun function) {
		nowTimeFun = function;
	}

	public static interface NowTimeFun {
		Date getNowTime();
	}

	/**
	 * 获取当前时间的上个月
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取当前时间的上周
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1); // 设置为上一周
		date = calendar.getTime();
		return date;
	}

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	/**
	 *
	 * 获取当前时间的前几天时间，或者后几天时间 @param day
	 * 正数：获取后几天的当前时间；负数：获取前几天的当前时间 @param @return String @throws
	 */
	public static Date getNowBeforeOrAfterTime(Integer day) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		date = calendar.getTime();
		return date;
	}

	/**
	 *
	 * 获取当前时间的前几天时间，或者后几天时间 @param day
	 * 正数：获取后几天的当前时间；负数：获取前几天的当前时间 @param @return String @throws
	 */
	public static Date getNowBeforeOrAfterTime(Date date, Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		date = calendar.getTime();
		return date;
	}

	public static Date parseUsefulTime(String usefulTime, Date beginTime) {
		return parseUsefulTime(usefulTime, beginTime, null);
	}

	public static Date parseUsefulTime(String usefulTime, Date beginTime, String defaultUsefulTime) {
		Date date = null;
		usefulTime = StringUtils.defaultIfBlank(usefulTime, defaultUsefulTime);

		if (StringUtils.isNotBlank(usefulTime)) {
			if (StringUtils.endsWithIgnoreCase(usefulTime, "h")) {
				date = DateUtils.addHours(beginTime,
						Integer.parseInt(usefulTime.substring(0, usefulTime.length() - 1)));
			} else if (StringUtils.endsWithIgnoreCase(usefulTime, "d")) {
				date = DateUtils.addDays(beginTime, Integer.parseInt(usefulTime.substring(0, usefulTime.length() - 1)));
			} else if (StringUtils.endsWithIgnoreCase(usefulTime, "m")) {
				date = DateUtils.addMonths(beginTime,
						Integer.parseInt(usefulTime.substring(0, usefulTime.length() - 1)));
			} else if (StringUtils.endsWithIgnoreCase(usefulTime, "y")) {
				date = DateUtils.addYears(beginTime,
						Integer.parseInt(usefulTime.substring(0, usefulTime.length() - 1)));
			}
		}

		return date;
	}

	/**
	 *
	 * <br>获取上周一:
	 * Date lastMonday = getDayOfWeek(new Date(), 1, -1);
	 *</br>
	 * <br>
	 * 获取本周三:
	 * Date wednesday = getDayOfWeek(new Date(), 4, 0);
	 *</br>
	 * <br>
	 * 获取下一周日:
	 * Date nextSunday = getDayOfWeek(new Date(), 7, 1);
	 *</br>
	 * @param date 起始日期,我们要在这一周的基础上计算
	 * @param dayOfWeek 要获取的星期数,范围1到7,1代表星期一
	 * @param offset 要偏移的周数,可以为零或正负值
	 * @return
	 */
	public static Date getDayOfWeek(Date date, int dayOfWeek, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int diff = dayOfWeek - currentDayOfWeek;
		c.add(Calendar.DATE, offset * 7 + diff);
		return c.getTime();
	}

}
