package com.tqmars.cardrecycle.application.sale;

import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.services.sale.ISaleDomainService;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by jjh on 1/24/17.
 */
public class SaleAppService extends BaseAppService implements ISaleAppService {
    ISaleDomainService _saleDomainService;

    public SaleAppService(ISaleDomainService _saleDomainService) {
        this._saleDomainService = _saleDomainService;
    }

    @Override
    public void sale1Card(Sale1CardInput input) {
//        ApiResult result = _saleDomainService.sell1Card(input);
//        return result;
    }

    @Async
    @Override
    public void saleListCard(List<Sale1CardInput> list) {
        _saleDomainService.sellListCard(list);
    }
}
