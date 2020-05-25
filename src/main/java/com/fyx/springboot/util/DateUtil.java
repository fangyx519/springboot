package com.fyx.springboot.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * @Author fyx
 * @Time in 22:02 2020/5/25
 * @Despriction
 */
public class DateUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static java.util.Date getUtilDate(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    /*
    * 时间是：2020-05-25
    */
    public static java.sql.Date getSqlDate(Date date){
        return new java.sql.Date(date.getTime());
    }

    /*
    *时间是：2020-05-25 22:25:17
    */
    public static String dateToString(Date date){
        return simpleDateFormat.format(date);
    }

    /*
    *时间是：2020-05-25 22:25:17.476
    */
    public static Timestamp getTimeStamp(Date date){
        return new Timestamp(date.getTime());
    }
}
