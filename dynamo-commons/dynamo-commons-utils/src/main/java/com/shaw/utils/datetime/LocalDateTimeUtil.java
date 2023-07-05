package com.shaw.utils.datetime;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.time.*;
import java.time.temporal.Temporal;

/**
 * java8 时间工具类
 *
 * @author shaw
 * @date 2023/06/20
 */
@UtilityClass
public class LocalDateTimeUtil {

    /**
     * 是否在指定的时间范围内
     */
    public boolean between(LocalDateTime now, LocalDateTime start, LocalDateTime end) {
        return ge(now, start) && le(now, end);
    }

    public static Duration between(LocalDateTime startTimeInclude, LocalDateTime endTimeExclude) {
        return between(startTimeInclude, endTimeExclude);
    }

    public static Duration between(Temporal startTimeInclude, Temporal endTimeExclude) {
        return Duration.between(startTimeInclude, endTimeExclude);
    }

    /**
     * 大于
     */
    public boolean gt(LocalDateTime now, LocalDateTime next) {
        long mills = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long epochMilli = next.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return mills > epochMilli;
    }

    /**
     * 小于
     */
    public boolean lt(LocalDateTime now, LocalDateTime next) {
        long mills = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long epochMilli = next.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return mills < epochMilli;
    }

    /**
     * 大于等于
     */
    public boolean ge(LocalDateTime now, LocalDateTime next) {
        long mills = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long epochMilli = next.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return mills >= epochMilli;
    }

    /**
     * 小于等于
     */
    public boolean le(LocalDateTime now, LocalDateTime next) {
        long mills = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long epochMilli = next.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return mills <= epochMilli;
    }

    /**
     * 将localDate转换成localDateTime
     */
    public LocalDateTime date2DateTime(LocalDate localDate) {
        return localDate.atTime(0, 0);

    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     *
     * @param timestamp 时间戳
     * @return LocalDateTime
     */
    public LocalDateTime parse(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime 日期时间
     * @return timestamp
     */
    public long timestamp(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    public LocalDateTime of(long epochMilli) {
        return of(Instant.ofEpochMilli(epochMilli));
    }

    public LocalDateTime of(Instant instant) {
        return of(instant, ZoneId.systemDefault());
    }

    public LocalDateTime of(Instant instant, ZoneId zoneId) {
        return null == instant ? null
                : LocalDateTime.ofInstant(instant, ObjectUtils.defaultIfNull(zoneId, ZoneId.systemDefault()));
    }
}
