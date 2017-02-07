package com.tqmars.cardrecycle.domain.services.sale;

import com.tqmars.cardrecycle.application.api.dto.SellCardInput;
import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;

import java.util.List;

/**
 * Created by jjh on 1/22/17.
 */
public interface ISaleDomainService {
    ApiResult sell1Card(Sale1CardInput input);
    void sellListCard(List<Sale1CardInput> list);
    ApiResult sell1CardApi(SellCardInput input);
}
