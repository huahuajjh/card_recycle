package com.tqmars.test.infrastructure.repositories;

import com.google.gson.reflect.TypeToken;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordInput;
import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.StringTools.OrderNumGenerator;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import org.junit.Test;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
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
//        String s = "[{\"name\":\"zs\",\"age\":\"12\"},{\"name\":\"zs\",\"age\":\"11\"},{\"name\":\"zs\",\"age\":\"13\"}]";
//        System.out.println(((P)(Serialization.toList(s).get(2))).getAge());
//        Type t = new TypeToken<List<P>>(){}.getType();
//        System.out.println(((P)Serialization.toList(s,t).get(0)).getName());
        System.out.println(DateTool.getInstance().getNowSqlTime());

    }

    public class P{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "P{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
