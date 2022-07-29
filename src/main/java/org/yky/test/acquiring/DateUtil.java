package org.yky.test.acquiring;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class DateUtil {

    public static final String FMT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String FMT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String FMT_yyyyMMdd_HH_mm_ss_SSS = "yyyyMMdd HH:mm:ss.SSS";
    public static final String FMT_yyyyMMdd_HH_mm_ss = "yyyyMMdd HH:mm:ss";
    public static final String FMT_yyyyMMdd = "yyyyMMdd";
    public static final String FMT_yyMMdd = "yyMMdd";
    public static final String FMT_ddMMyyyy = "ddMMyyyy";
    public static final String FMT_MMM = "MMM";
    public static final String FMT_HHmmssSSS = "HHmmssSSS";

    public static final String FMT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String FMT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FMT_dd_MMM_yyyy = "dd-MMM-yyyy";
    public static final String FMT_M_d = "M-d";

    public static final String FMT_SPACE_dd_MMM_yyyy = "dd MMM yyyy";
    public static final String FMT_SPACE_dd_MMM = "dd MMM";

    public static final String FMT_DAILY_END_TIME = "235959";


    private static final Map<String, DateTimeFormatter> pattern2Formatter = new HashMap<>();

    static {
        Field[] fields = DateUtil.class.getDeclaredFields();
        try {
            for (Field f : fields) {
                if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())
                        && f.getName().startsWith("FMT")) {
                    String pattern = (String) f.get(DateUtil.class);
//                    log.info("init date formatter, pattern:{}", pattern);
                    getFormatter(pattern);
                }
            }
        } catch (Exception e) {
            log.error("DateUtilinitError" + e.getMessage(), e);
        }
    }

    public static String now(String pattern) {
        return LocalDateTime.now().format(getFormatter(pattern));
    }

    public static Date nowDate(long minusSeconds) {
        Instant instant = LocalDateTime.now()
                .minusSeconds(minusSeconds)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(getFormatter(pattern));
    }

    public static String format(LocalDate localDate, String pattern) {
        return localDate.format(getFormatter(pattern));
    }

    public static String format(Date Date, String pattern) {
        LocalDateTime ldt = Instant.ofEpochMilli(Date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ldt.format(getFormatter(pattern));
    }

    public static String convert(String dt, String from, String to) {
        LocalDateTime src = LocalDateTime.parse(dt, getFormatter(from));
        return src.format(getFormatter(to));
    }

    public static String convertFromDate(String dt, String from, String to) {
        LocalDate src = LocalDate.parse(dt, getFormatter(from));
        return src.format(getFormatter(to));
    }

    public static String formatWithLanguageWithoutException(Date date, String pattern, Locale language) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, language);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            // 不可抛异常
            log.warn("date format error [time={}][pattern={}][language={}]", date, pattern, language, e);
            return null;
        }
    }


    public static LocalDateTime parseToLdt(String dt, String from) {
        return LocalDateTime.parse(dt, getFormatter(from));
    }

    public static boolean isSameDay(String dt1, String dt2, String pattern) {
        LocalDateTime ldt1 = parseToLdt(dt1, pattern);
        LocalDateTime ldt2 = parseToLdt(dt2, pattern);
        return ldt1.getYear() == ldt2.getYear() && ldt1.getDayOfYear() == ldt2.getDayOfYear();
    }


    public static String toEpochMilli(LocalDateTime ldt) {
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + "";
    }

    /*
     * ============================================================
     */

    private static DateTimeFormatter getFormatter(String pattern) {
        DateTimeFormatter formatter = pattern2Formatter.get(pattern);
        if (formatter == null) {
            synchronized (pattern2Formatter) {
                formatter = pattern2Formatter.get(pattern);
                if (formatter == null) {
                    formatter = DateTimeFormatter.ofPattern(pattern);
                    pattern2Formatter.put(pattern, formatter);
                }
            }
        }
        return formatter;
    }

    /**
     * yyyy-MM-dd
     * 获取偏移量的日期，比如offset = 1，即获取明天， -1则为昨天
     *
     * @param date
     * @param offset
     * @return
     */
    public static String getNextNDate(Date date, int offset) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.plusDays(offset).format(getFormatter(FMT_yyyy_MM_dd));
    }

    /**
     * 获取今天属于第个分片
     *
     * @param date
     * @param split 分片大小
     * @return
     */
    public static Integer getDateIndex(Date date, int split) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int dayOfYear = localDate.getDayOfYear();
        int x = (int) Math.ceil((double) dayOfYear / split);
        return x;
    }

    public static String getYear(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear() + "";
    }
}
