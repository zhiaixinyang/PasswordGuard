package com.mdove.passwordguard.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author MDove on 2018/2/10.
 */
public class DateUtil {

    public static final long SECOND_IN_MILLIS = 1000;
    public static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
    public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
    public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;
    public static final long FOUR_HOURS_IN_MILLIS = HOUR_IN_MILLIS * 4;
    public static final long WEEK_IN_MILLIS = DAY_IN_MILLIS * 7;
    public static final long MONTH_IN_MILLIS = DAY_IN_MILLIS * 30;

    /**
     * This constant is actually the length of 364 days, not of a year!
     */
    public static final long YEAR_IN_MILLIS = WEEK_IN_MILLIS * 52;
    private static ThreadLocal<DateFormat> DEFAULT_DATE_FORMAT = new ThreadLocalDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ThreadLocalDateFormat extends ThreadLocal<DateFormat> {
        private String mDatePattern;

        public ThreadLocalDateFormat(String datePattern) {
            super();
            mDatePattern = datePattern;
        }

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(mDatePattern, Locale.US);
        }
    }

    public static String formatDefault(long time) {
        return DEFAULT_DATE_FORMAT.get().format(new Date(time));
    }

    public static String simpleFormat(long time, @NonNull String datePattern) {
        Date date = new Date(time);
        DateFormat format = new SimpleDateFormat(datePattern);
        return format.format(date);
    }

    public static String simpleFormat(@NonNull String datePattern) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(datePattern);
        return format.format(date);
    }

    public static boolean isInDay(long time) {
        long current = System.currentTimeMillis();
        if (current - time <= DAY_IN_MILLIS && current - time > 0) {
            return true;
        }
        return false;
    }

    public static boolean isFourHours(long time) {
        long current = System.currentTimeMillis();
        if (current - time <= FOUR_HOURS_IN_MILLIS && current - time > 0) {
            return true;
        }
        return false;
    }

    public static boolean isSameDay(long time1, long time2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time1));
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(new Date(time2));
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);
        return day1 == day2;
    }

    public static boolean isToday(long time) {
        return isSameDay(time, System.currentTimeMillis());
    }

    public static int getHourOfDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MINUTE);
    }

    public static int getDayInMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfWeek(String timeZone, long timestamp) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timestamp);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getSimpleMonth(boolean abbrev) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return parseMonth(month, abbrev);
    }

    //HH为24小时进制
    public static String getDateChinese(Date time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(time);
    }

    public static String getDateChinese(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        return format.format(new Date());
    }

    private static String parseMonth(int month, boolean abbrev) {
        switch (month) {
            case Calendar.JANUARY:
                return abbrev ? "Ja." : "January";
            case Calendar.FEBRUARY:
                return abbrev ? "Feb." : "February";
            case Calendar.MARCH:
                return abbrev ? "Mar." : "March";
            case Calendar.APRIL:
                return abbrev ? "Apr." : "April";
            case Calendar.MAY:
                return abbrev ? "May." : "May";
            case Calendar.JUNE:
                return abbrev ? "Jun." : "June";
            case Calendar.JULY:
                return abbrev ? "Jul." : "July";
            case Calendar.AUGUST:
                return abbrev ? "Aug." : "August";
            case Calendar.SEPTEMBER:
                return abbrev ? "Sep." : "September";
            case Calendar.OCTOBER:
                return abbrev ? "Oct." : "October";
            case Calendar.NOVEMBER:
                return abbrev ? "Nov." : "November";
            case Calendar.DECEMBER:
                return abbrev ? "Dec." : "December";
            default:
                return "";
        }
    }

    public static String getSimpleWeek(boolean abbrev) {
        Calendar calendar = Calendar.getInstance();
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        return parseWeek(weekday, abbrev);
    }

    private static String parseWeek(int weekday, boolean abbrev) {
        String dayInWeek;
        switch (weekday) {
            case Calendar.SUNDAY:
                dayInWeek = abbrev ? "周日" : "星期天";
                break;
            case Calendar.MONDAY:
                dayInWeek = abbrev ? "周一" : "星期一";
                break;
            case Calendar.TUESDAY:
                dayInWeek = abbrev ? "周二" : "星期二";
                break;
            case Calendar.WEDNESDAY:
                dayInWeek = abbrev ? "周三" : "星期三";
                break;
            case Calendar.THURSDAY:
                dayInWeek = abbrev ? "周四" : "星期四";
                break;
            case Calendar.FRIDAY:
                dayInWeek = abbrev ? "周五" : "星期五";
                break;
            case Calendar.SATURDAY:
                dayInWeek = abbrev ? "周六" : "星期六";
                break;
            default:
                dayInWeek = abbrev ? "周日" : "星期天";
                break;
        }
        return dayInWeek;
    }

    public static String getSimpleWeek(@Nullable String timeZone, long timestamp, boolean abbrev) {
        if (TextUtils.isEmpty(timeZone)) {
            timeZone = TimeZone.getDefault().getDisplayName();
        }
        int dayOfWeek = getDayOfWeek(timeZone, timestamp);
        return parseWeek(dayOfWeek, abbrev);
    }
}
