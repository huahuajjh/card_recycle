package com.tqmars.cardrecycle.infrastructure.StringTools;

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

}
