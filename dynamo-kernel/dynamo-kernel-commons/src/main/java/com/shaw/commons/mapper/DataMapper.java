package com.shaw.commons.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author shaw
 * @date 2023/7/18
 */
public class DataMapper {

	public LocalDateTime asLocalDateTime(Date date) {
		return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
	}

	public Date asDate(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			//获取系统默认时区
			ZoneId zoneId = ZoneId.systemDefault();
			//时区的日期和时间
			ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
			return Date.from(zonedDateTime.toInstant());
		}
		return null;
	}

}
