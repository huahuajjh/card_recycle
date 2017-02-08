package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.card.ICardTypeAppService;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jjh on 2/3/17.
 */
public class TestCardTypeAppService {
    ICardTypeAppService service;

    @Before
    public void before(){
        service = ServiceLocator.getInstance().getService("CardTypeAppService", ICardTypeAppService.class);
    }

    @Test
    public void testQueryCardTypeList(){
        service.queryCardTypeList().forEach(a->System.out.println(a.toString()));
    }

    @Test
    public void testQueryCardTypeAndItemsList(){
        service.queryCardTypeAndItem().forEach(o->System.out.println(o));
    }
}
