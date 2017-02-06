package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.sale.ISaleAppService;
import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.SaleCardApi;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.OrderNumGenerator;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by jjh on 2/5/17.
 */
public class TestSale1Card {
    @Test
    public void testSale1Card() throws Exception {
//        ApiResult result = SaleCardApi.sale1Card("SZX","50","77111111111111212","121212121212121212", OrderNumGenerator.generateOrderNum());
//        System.out.println(result.toString());

        ISaleAppService saleAppService = ServiceLocator.getInstance().getService("SaleAppService",ISaleAppService.class);
        Sale1CardInput input = new Sale1CardInput();
        input.setCardNum("11111111111111118");
        input.setCardCode("SZX");
        input.setCardItemId(23);
        input.setCardPwd("123456789454545874");
        input.setUserId(1);
        input.setCardTypeId(8);
        ApiResult r = saleAppService.sale1Card(input);
        p(r);

    }

    @Test
    public void testDecimal(){
//        p(new BigDecimal("50").multiply(BigDecimal.valueOf(0.88)));
//        p(DateTool.getInstance().getNowTime("yyyy-MM-dd"));
    }

    private void p(Object o){
        System.out.println(o);
    }

}
