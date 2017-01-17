package com.tqmars.cardrecycle.infrastructure.StringTools;

import java.util.UUID;

/**
 * Created by jjh on 1/14/17.
 */
public class OrderNumGenerator {
    public static String generateOrderNum(){
        return System.currentTimeMillis()+String.valueOf(UUID.randomUUID().toString().hashCode());

    }
}
