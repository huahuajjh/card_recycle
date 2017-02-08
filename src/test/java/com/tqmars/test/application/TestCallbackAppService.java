package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.callback.ICallbackAppService;
import com.tqmars.cardrecycle.application.callback.dto.OrderCallbackInput;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

/**
 * Created by jjh on 2/6/17.
 */
public class TestCallbackAppService {
    @Test
    public void testOrderCallback(){
        ICallbackAppService service = ServiceLocator.getInstance().getService("CallbackAppService",ICallbackAppService.class);
        OrderCallbackInput input = new OrderCallbackInput("1",
                "123",
                "1486401479669951539202",
                "done",
                "20",
                "20",
                "asd");

        service.orderCallbank(input);
    }
}
