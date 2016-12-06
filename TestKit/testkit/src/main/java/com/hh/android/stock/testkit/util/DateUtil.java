package com.hh.android.stock.testkit.util;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 * Created by litj on 2016/11/21.
 */
public class DateUtil {

    public static final String DATA_STYLE_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_STYLE_2 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String DATA_STYLE_3 = "yyyy-MM-dd";

    /**
     * 默认格式化日期 把2015-05-22 18:40:23格式转为 2015年05月22日 18:40:23
     *
     * @param dateStr
     * @return
     */
    public static String formatDate(String dateStr) {
        return formatDate(dateStr, DATA_STYLE_1, DATA_STYLE_2);
    }

    /**
     * 默认格式化日期 把2015-05-22 18:40:2 格式转为指定格式
     *
     * @param dateStr
     * @param formatStr 要转换的格式
     * @return
     */
    public static String formatDate(String dateStr, String formatStr) {
        return formatDate(dateStr, DATA_STYLE_1, formatStr);
    }



    /**
     * 自定义格式化时间
     *
     * @param dateStr       日期
     * @param dateFormatStr 传入的日期格式
     * @param formatStr     得到的日期格式
     * @return
     */
    public static String formatDate(String dateStr, String dateFormatStr, String formatStr) {
        if (!TextUtils.isEmpty(dateStr)) {
            Date date;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
                date = null;
                try {
                    date = sdf.parse(dateStr);
                } catch (ParseException e) {
                    Log.d(DateUtil.class.getName(),e.getMessage());
                }
                return new SimpleDateFormat(formatStr).format(date);
            } catch (Exception e) {
                Log.d(DateUtil.class.getName(),e.getMessage());
            }
            return dateStr;
        }
        return "";
    }

    /**
     * 传进来的是否是今天
     * @param date 日期
     * @param format 格式
     * @return 是否今天
     */
    public static boolean isToday(String date, String format){
        return getNowTime().equals(formatDate(date, format, DATA_STYLE_3));
    }

    /**
     * 获得当前时间
     * @return 结果
     */
    public static String getNowTime(){
        return getNowTime(DATA_STYLE_3);
    }

    public static String getNowTime(String formatStr){
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date(System.currentTimeMillis()));
    }

}
