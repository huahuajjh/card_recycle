package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.admin.order.IAdminOrderAppService;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

/**
 * Created by jjh on 2/7/17.
 */
public class TestAdminOrderAppservice {
    @Test
    public void testAdminOrderQuery(){
        IAdminOrderAppService service = ServiceLocator.getInstance().getService("AdminOrderAppService",IAdminOrderAppService.class);
        QueryOrderListInput input = new QueryOrderListInput();
        input.setIndex(1);
        input.setCount(5);
        System.out.println(service.queryOrderList(input));
    }
}
