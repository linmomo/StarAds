package com.starwinwin.lib_stay_base.utils;

import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间转换显示类
 * 人性化显示时间
 */
public class TimeTransitionUtils {

    private static SimpleDateFormat formatBuilder;
    public static final int WEEKDAYS = 7;
    public static String[] WEEK = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    /**
     * 返回开始时间距离当前时间的时间差
     *
     * @param startTime
     * @return
     */
    public static String twoDateDistance(String startTime) {
        return twoDateDistance(startTime, null);
    }

    /**
     * 返回开始时间与结束时间的时间差
     * 问题：几周前不起作用
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 几秒，几分，几天，几小时，几周
     */
    public static String twoDateDistance(String startTime, String endTime) {

        Date endDate = null;

        if (TextUtils.isEmpty(startTime)) {
            return "";
        }

        if (TextUtils.isEmpty(endTime)) {
            endDate = new Date(System.currentTimeMillis());
        } else {
            endDate = TimeUtils.string2Date(endTime);
        }

        Date startDate = TimeUtils.string2Date(startTime);

        if (startDate == null || endDate == null) {
            return null;
        }

        //计算时间差
        long timeLong = endDate.getTime() - startDate.getTime();
        if (timeLong <= 0) {
            return "刚刚";
        } else if (timeLong < 60 * 1000) {//一分钟内
            return timeLong / 1000 + "秒前";
        } else if (timeLong < 60 * 60 * 1000) {//一小时内
            timeLong = timeLong / 1000 / 60;
            return timeLong + "分钟前";
        } else if (timeLong < 60 * 60 * 24 * 1000) {//一天内
            timeLong = timeLong / 60 / 60 / 1000;
            return timeLong + "小时前";
        } else if (timeLong < 60 * 60 * 24 * 1000 * 7) {//一周内
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        } else {//一周之外
            timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
            return timeLong + "周前";
        }
//           else if (timeLong > 60 * 60 * 24 * 1000 * 7 * 4) {//四周内  2419200000
//               timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
//
//               return timeLong + "周前";
//           }
//           else {
//               timeLong = timeLong / 1000 / 60 / 60 / 24;
//               return timeLong + "天前";
//           }
    }

    /********************************************************************/

    /**
     * UTM转换成简单日期描述，如三周前，上午，昨天等
     *
     * @param time       需要转换的时间
     * @param isShowWeek 是否采用周形式显示  true 显示为3周前，false 则显示为时间格式mm-dd
     * @return
     */
    public static String getTimeDesc(String time, boolean isShowWeek) {
        return getTimeDesc(TimeUtils.string2Date(time).getTime(), isShowWeek);
    }

    /**
     * 时间戳转换成简单日期描述，如三周前，上午，昨天等
     *
     * @param milliseconds milliseconds
     * @param isShowWeek   是否采用周的形式显示  true 显示为3周前，false 则显示为时间格式mm-dd
     * @return 如三周前，上午，昨天等
     */

    public static String getTimeDesc(long milliseconds, boolean isShowWeek) {
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        long hour = calendar.get(Calendar.HOUR_OF_DAY);

        calendar.setTimeInMillis(System.currentTimeMillis());
        long hourNow = calendar.get(Calendar.HOUR_OF_DAY);

        Log.e("---------->---", System.currentTimeMillis() + "----------" + milliseconds);
        long datetime = System.currentTimeMillis() - (milliseconds);
        long day = (long) Math.floor(datetime / 24 / 60 / 60 / 1000.0f) + (hourNow < hour ? 1 : 0);// 天前

        if (day <= 7) {// 一周内
            if (day == 0) {// 今天
                if (hour <= 4) {
                    sb.append(" 凌晨 ");
                } else if (hour > 4 && hour <= 6) {
                    sb.append(" 早上 ");
                } else if (hour > 6 && hour <= 11) {
                    sb.append(" 上午 ");
                } else if (hour > 11 && hour <= 13) {
                    sb.append(" 中午 ");
                } else if (hour > 13 && hour <= 18) {
                    sb.append(" 下午 ");
                } else if (hour > 18 && hour <= 19) {
                    sb.append(" 傍晚 ");
                } else if (hour > 19 && hour <= 24) {
                    sb.append(" 晚上 ");
                } else {
                    sb.append("今天 ");
                }
            } else if (day == 1) {// 昨天
                sb.append(" 昨天 ");
            } else if (day == 2) {// 前天
                sb.append(" 前天 ");
            } else {
                sb.append(" " + DateToWeek(milliseconds) + " ");
            }
        } else {// 一周之后
            if (isShowWeek) {
                sb.append((day % 7 == 0 ? (day / 7) : (day / 7 + 1)) + "周前");
            } else {
                formatBuilder = new SimpleDateFormat("MM-dd");
                String time = formatBuilder.format(milliseconds);
                sb.append(time);
            }
        }
        Log.e("sb---", sb.toString() + "");
        return sb.toString();

    }

    /**
     * 时间戳转换成简单日期描述，如三周前，上午，昨天等
     *
     * @param time 需要转换的时间
     * @return
     */
    public static String getTimeDesc(String time) {
        return getTimeDesc(TimeUtils.string2Date(time).getTime());
    }

    /**
     * 时间戳转换成日期描述，如三周前，上午，昨天等
     *
     * @param milliseconds 时间
     */
    public static String getTimeDesc(long milliseconds) {

        return getTimeDesc(milliseconds, true);
    }

    /**
     * 时间戳转换成带具体描述的日期
     *
     * @param time 待转换日期
     * @return UTM转换成带描述的日期
     */
    public static String getDisplayTimeAndDesc(String time) {
        return getDisplayTimeAndDesc(TimeUtils.string2Date(time).getTime());
    }

    /**
     * 时间戳转换成带具体描述的日期
     *
     * @param milliseconds milliseconds
     * @return UTM转换成带描述的日期
     */

    public static String getDisplayTimeAndDesc(long milliseconds) {
        formatBuilder = new SimpleDateFormat("HH:mm");
        String time = formatBuilder.format(milliseconds);
        Log.e("time", time);
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        long hour = calendar.get(Calendar.HOUR_OF_DAY);
        long datetime = System.currentTimeMillis() - (milliseconds);
        long day = (long) Math.ceil(datetime / 24 / 60 / 60 / 1000.0f);// 天前
        Log.v("day---hour---time---", day + "----" + hour + "-----" + time);

        if (day <= 7) {// 一周内
            if (day == 0) {// 今天
                if (hour <= 4) {
                    sb.append(" 凌晨 " + time);
                } else if (hour > 4 && hour <= 6) {
                    sb.append(" 早上 " + time);
                } else if (hour > 6 && hour <= 11) {
                    sb.append(" 上午 " + time);
                } else if (hour > 11 && hour <= 13) {
                    sb.append(" 中午 " + time);
                } else if (hour > 13 && hour <= 18) {
                    sb.append(" 下午 " + time);
                } else if (hour > 18 && hour <= 19) {
                    sb.append(" 傍晚 " + time);
                } else if (hour > 19 && hour <= 24) {
                    sb.append(" 晚上 " + time);
                } else {
                    sb.append("今天 " + time);
                }
            } else if (day == 1) {// 昨天
                sb.append("昨天 " + time);
            } else if (day == 2) {// 前天
                sb.append("前天 " + time);
            } else {
                sb.append(DateToWeek(milliseconds) + time);
            }
        } else {// 一周之前
            sb.append(day % 7 + "周前");
        }
        Log.e("sb---", sb.toString() + "");
        return sb.toString();

    }

    /**
     * 日期变量转成对应的星期字符串
     *
     * @param milliseconds data
     * @return 日期变量转成对应的星期字符串
     */
    public static String DateToWeek(long milliseconds) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }

        return WEEK[dayIndex - 1];
    }

    /**
     * 将时间与系统时间的间隔转换成描述性字符串，如2天前，3月1天后等。
     *
     * @param time   相对的时间字符串
     * @param isFull 是否全部显示 true 全部显示 false 简单显示
     * @return 将时间间隔转换成描述性字符串，如2天前，3月1天后等。
     */
    public static String diffDateAsDesc(String time, boolean isFull) {
        return diffDateAsDesc(TimeUtils.string2Date(time), isFull);
    }

    /**
     * 将时间与系统时间的间隔转换成描述性字符串，如2天前，3月1天后等。
     *
     * @param toDate 相对的日期
     * @param isFull 是否全部显示 true 全部显示 false 简单显示
     * @return 将时间间隔转换成描述性字符串，如2天前，3月1天后等。
     */
    public static String diffDateAsDesc(Date toDate, boolean isFull) {
        String diffDesc = "";
        String fix = "";
        Long diffTime;
        Date curDate = new Date();
        if (curDate.getTime() > toDate.getTime()) {
            diffTime = curDate.getTime() - toDate.getTime();
            fix = "前";
        } else {
            diffTime = toDate.getTime() - curDate.getTime();
            fix = "后";
        }

        //换算成分钟数，防止Int溢出。
        diffTime = diffTime / 1000 / 60;

        Long year = diffTime / (60 * 24 * 30 * 12);
        diffTime = diffTime % (60 * 24 * 30 * 12);
        if (year > 0) {
            diffDesc = diffDesc + year + "年";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long month = diffTime / (60 * 24 * 30);
        diffTime = diffTime % (60 * 24 * 30);
        if (month > 0) {
            diffDesc = diffDesc + month + "月";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long day = diffTime / 60 / 24;
        diffTime = diffTime % (60 * 24);
        if (day > 0) {
            diffDesc = diffDesc + day + "天";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long hour = diffTime / (60);
        diffTime = diffTime % (60);
        if (hour > 0) {
            diffDesc = diffDesc + hour + "时";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        Long minitue = diffTime;
        if (minitue > 0) {
            diffDesc = diffDesc + minitue + "分";
            if (!isFull) {
                return diffDesc + fix;
            }
        }

        return diffDesc + fix;
    }

    /*******************************************************************/

    /**
     * 人性化时间显示
     *
     * @param date
     *            时间字符串
     * @return 格式化的时间
     */
    public static String formatStringTime(String date) {
        return formatDateTime(TimeUtils.string2Date(date, new SimpleDateFormat(TimeUtils.FORMAT_YMDHMS, Locale.CHINA)));
    }

    /**
     * 人性化时间显示
     *
     * @param date
     *            date时间对象
     * @return 格式化的时间
     */
    public static String formatDateTime(Date date) {
        String text;
        long dateTime = date.getTime();
        if (isSameDay(dateTime)) {
            Calendar calendar = GregorianCalendar.getInstance();
            // 一分钟之内
            if (inOneMinute(dateTime, calendar.getTimeInMillis())) {
                return "刚刚";
                // 一小时之内
            } else if (inOneHour(dateTime, calendar.getTimeInMillis())) {
                return String.format("%d分钟之前",Math.abs(dateTime - calendar.getTimeInMillis()) / 60000);
            } else {
                calendar.setTime(date);
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                // 17点之后
                if (hourOfDay > 17) {
                    text = "晚上 hh:mm";
                    // 0到6点
                } else if (hourOfDay >= 0 && hourOfDay <= 6) {
                    text = "凌晨 hh:mm";
                    // 11点到17点
                } else if (hourOfDay > 11 && hourOfDay <= 17) {
                    text = "下午 hh:mm";
                } else {
                    text = "上午 hh:mm";
                }
            }
        } else if (isYesterday(dateTime)) {
            text = "昨天 HH:mm";
        } else if (isBeforeYesterday(dateTime)) {
            text = "前天 HH:mm";
        } else if (isSameYear(dateTime)) {
            text = "M月d日 HH:mm";
        } else {
            text = "yyyy-M-d HH:mm";
        }

        // 注意，如果使用android.text.format.DateFormat这个工具类，在API 17之前它只支持adEhkMmszy
        return new SimpleDateFormat(text, Locale.CHINA).format(date);
    }

    /** 一分钟之内，刚刚 */
    private static boolean inOneMinute(long time1, long time2) {
        return Math.abs(time1 - time2) < 60000;
    }

    /** 一小时之内，刚刚 */
    private static boolean inOneHour(long time1, long time2) {
        return Math.abs(time1 - time2) < 3600000;
    }


    /** 该时间是否为今天 */
    private static boolean isSameDay(long time) {
        long startTime = floorDay(Calendar.getInstance()).getTimeInMillis();
        long endTime = ceilDay(Calendar.getInstance()).getTimeInMillis();
        return time > startTime && time < endTime;
    }

    /** 该时间是否为昨天 */
    private static boolean isYesterday(long time) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.add(Calendar.DAY_OF_MONTH, -1);
        long startTime = startCal.getTimeInMillis();

        Calendar endCal;
        endCal = ceilDay(Calendar.getInstance());
        endCal.add(Calendar.DAY_OF_MONTH, -1);
        long endTime = endCal.getTimeInMillis();
        return time > startTime && time < endTime;
    }

    /** 该时间是否为前天 */
    private static boolean isBeforeYesterday(long time) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.add(Calendar.DAY_OF_MONTH, -2);
        long startTime = startCal.getTimeInMillis();

        Calendar endCal;
        endCal = ceilDay(Calendar.getInstance());
        endCal.add(Calendar.DAY_OF_MONTH, -2);
        long endTime = endCal.getTimeInMillis();
        return time > startTime && time < endTime;
    }

    /** 该时间是否为今年 */
    private static boolean isSameYear(long time) {
        Calendar startCal;
        startCal = floorDay(Calendar.getInstance());
        startCal.set(Calendar.MONTH, Calendar.JANUARY);
        startCal.set(Calendar.DAY_OF_MONTH, 1);
        return time >= startCal.getTimeInMillis();
    }


    /** 设置时间为00：00：00：0 */
    private static Calendar floorDay(Calendar startCal) {
        // 将给定的日历字段设置为给定值。
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        return startCal;
    }

    /** 设置时间为23：59：59：999 */
    private static Calendar ceilDay(Calendar endCal) {
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        return endCal;
    }
}
