package com.tqmars.cardrecycle.infrastructure.StringTools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jjh on 1/14/17.
 */
public class DateTool {
    private static DateTool dt = new DateTool();
    private DateTool(){}


    public static DateTool getInstance(){
        return dt;
    }

    public String getNowTime(){
        Date now = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
        return s.format(now);
    }

    public String getNowTime(String format){
        Date now = new Date();
        SimpleDateFormat s = new SimpleDateFormat(format);
        return s.format(now);
    }

    public Timestamp getNowSqlTime(){
        return Timestamp.valueOf(getNowTime("yyyy-MM-dd HH:mm:ss"));
    }

    public java.sql.Date getSqlNowDate(){
        return java.sql.Date.valueOf(getNowTime("yyyy-MM-dd"));
    }

    public java.sql.Date getSqlNowDate(String format){
        return java.sql.Date.valueOf(getNowTime(format));
    }


}
