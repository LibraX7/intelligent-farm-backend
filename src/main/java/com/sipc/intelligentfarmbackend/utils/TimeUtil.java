package com.sipc.intelligentfarmbackend.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author
 */
public class TimeUtil {
    public static String getNowTime() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter1);
    }

    public static Date getDate() {
        long l = System.currentTimeMillis();
        return new Date(l);
    }

    public static String getTimeByDate(LocalDateTime date) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter1.format(date);
    }
    public static String getTimeByDateS(LocalDateTime date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static LocalDateTime getTimeByString(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
    public static LocalDateTime getTimeByStringS(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.parse(date, formatter);
    }
}
