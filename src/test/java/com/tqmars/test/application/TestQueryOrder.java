package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.admin.order.IAdminOrderAppService;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.application.order.IOrderAppService;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

/**
 * Created by jjh on 2/6/17.
 */
public class TestQueryOrder {
    @Test
    public void testQueryOrder(){
        IOrderAppService service = ServiceLocator.getInstance().getService("OrderAppService",IOrderAppService.class);
        QueryOrderListInput input = new QueryOrderListInput();
//        input.setFrom(Date.valueOf("2016-01-01"));
//        input.setTo(Date.valueOf("2017-03-01"));
        input.setIndex(1);
        input.setCount(10);
//        System.out.println(service.queryOrderList(input));
    }

    @Test
    public void testAdminQueryOrder(){
        IAdminOrderAppService service = ServiceLocator.getInstance().getService("AdminOrderAppService",IAdminOrderAppService.class);
        QueryOrderListInput input = new QueryOrderListInput();
//        input.setFrom(Date.valueOf("2016-01-01"));
//        input.setTo(Date.valueOf("2017-03-01"));
        input.setIndex(1);
        input.setCount(10);
        System.out.println(service.queryOrderList(input));
    }
}
