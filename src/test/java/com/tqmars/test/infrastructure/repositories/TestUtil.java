package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.StringTools.OrderNumGenerator;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * Created by jjh on 1/13/17.
 */
public class TestUtil {
    @Test
    public void testToEntity(){

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
        HashMap<String,Object> hs = new HashMap<>();
        for (int i = 0; i < 10000000; i++) {
            hs.put(OrderNumGenerator.generateOrderNum(),i);
        }
        System.out.println(hs.size());

    }

}
