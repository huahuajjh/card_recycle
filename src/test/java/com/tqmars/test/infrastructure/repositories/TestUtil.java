package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordInput;
import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.StringTools.OrderNumGenerator;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjh on 1/13/17.
 */
public class TestUtil {
    @Test
    public void testToEntity(){
        System.out.println(Md5.md5WithSalt("abcd123"));
    }

    @Test
    public void testToEntityList() throws InstantiationException, IllegalAccessException {

    }

    @Test
    public void testMd5() throws InterruptedException {
//        System.out.println(DigestUtils.md5Hex("123"));
//        System.out.println(Md5.md5("123"));
//        System.out.println(System.currentTimeMillis());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        HashMap<String,Object> hs = new HashMap<>();
//        for (int i = 0; i < 10000000; i++) {
//            hs.put(OrderNumGenerator.generateOrderNum(),i);
//        }
//        System.out.println(hs.size());

//        System.out.println(Serialization.toJsonWithFormatter("huahuajjh","success",600));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }

        list.forEach(
                (i)-> {
                    if(i>10){
                        System.out.println(i);
                    }
                }
        );

    }

    @Test
    public void testOrderNumGenerator(){
//        System.out.println(OrderNumGenerator.generateOrderNum().replace("-",""));
        System.out.println(OrderDetails.OrderStatus.FAIL);
    }

    @Test
    public void testDate(){
//        System.out.println(DateTool.getInstance().getNowTime("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(Date.valueOf("2014-01-01"));
//        System.out.println(Serialization.toObject("{\"index\":1,\"count\":15,\"from\":\"1990-01-01\",\"to\":\"2990-01-01\"}", QueryWithdrawRecordInput.class).getFrom());
//        System.out.println(Serialization.toObject("{\"index\":1,\"count\":15}", QueryWithdrawRecordInput.class).getCount());


//        System.out.println(new BigDecimal("50").compareTo(new BigDecimal("500")));

    }

}
