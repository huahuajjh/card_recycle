package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.wallet.IWalletAppService;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

/**
 * Created by jjh on 2/4/17.
 */
public class TestWalletAppService {
    @Test
    public void testQueryBalance(){
        IWalletAppService service = ServiceLocator.getInstance().getService("WalletAppService",IWalletAppService.class);
        System.out.println(service.getBalance(1));
    }

}
