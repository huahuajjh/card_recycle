package com.tqmars.cardrecycle.application.sale;

import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;

import java.util.List;

/**
 * Created by jjh on 1/21/17.
 */
public interface ISaleAppService {
    void sale1Card(Sale1CardInput input);
    void saleListCard(List<Sale1CardInput> list);
}
