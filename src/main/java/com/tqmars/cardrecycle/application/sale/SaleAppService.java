package com.tqmars.cardrecycle.application.sale;

import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.services.sale.ISaleDomainService;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;

/**
 * Created by jjh on 1/24/17.
 */
public class SaleAppService extends BaseAppService implements ISaleAppService {
    ISaleDomainService _saleDomainService;

    public SaleAppService(ISaleDomainService _saleDomainService) {
        this._saleDomainService = _saleDomainService;
    }

    @Override
    public ApiResult sale1Card(Sale1CardInput input) {
        ApiResult result = _saleDomainService.sell1Card(input);
        return result;
    }
}
