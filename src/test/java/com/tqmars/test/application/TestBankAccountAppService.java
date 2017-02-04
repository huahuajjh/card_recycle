package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.bank.IBankAppservice;
import com.tqmars.cardrecycle.application.bank.dto.AddBankAccountInput;
import com.tqmars.cardrecycle.application.card.ICardTypeAppService;
import com.tqmars.cardrecycle.domain.repositories.IBankAccountRepository;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jjh on 2/4/17.
 */
public class TestBankAccountAppService {
    IBankAppservice service;

    @Before
    public void before(){
        service = ServiceLocator.getInstance().getService("BankAccountAppService", IBankAppservice.class);
    }

    @Test
    public void testQueryCardTypeList(){
        System.out.println(service.queryAllBank());
    }

    @Test
    public void testAddBankACcount(){
        AddBankAccountInput input = new AddBankAccountInput();
        input.setCardNum("1515252525");
        input.setBankName("招商银行");
        input.setBankId(23);
        input.setName("zs");

        service.addBankAccount(input);
    }

    @Test
    public void testQueryBankAccount(){
        System.out.println(service.queryBankAccountById(1).toString());
    }
}
