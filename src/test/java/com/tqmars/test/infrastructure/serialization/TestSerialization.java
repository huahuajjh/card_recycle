package com.tqmars.test.infrastructure.serialization;

import com.tqmars.cardrecycle.application.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.serialization.Json;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

/**
 * Created by jjh on 1/11/17.
 */
public class TestSerialization {
    @Test
    public void testDateSerialization(){
        String str = "{\"index\":1,\"count\":15,\"from\":\"2017-03-09\",\"to\":\"2017-03-14\"}";
        QueryOrderListInput o = Serialization.toObject(str, QueryOrderListInput.class);
        System.out.println(o.getFrom());
    }

    @Test
    public void testTimeStamp(){
        Timestamp t1 = DateTool.getInstance().getNowSqlTime();
        Timestamp t = Timestamp.valueOf("yyyy-MM-dd HH:mm:ss");
        System.out.println(t);
    }

    @Test
    public void testJson(){
        Timestamp t = DateTool.getInstance().getNowSqlTime();
//        System.out.println(t);
//        System.out.println(Serialization.toJson(t));
        System.out.println(Json.getInstance().setDateFormat("yyyy-MM-dd HH:mm:ss").toJson(t));
    }

}
