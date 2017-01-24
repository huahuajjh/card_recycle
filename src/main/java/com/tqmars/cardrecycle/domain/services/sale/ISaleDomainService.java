package com.tqmars.cardrecycle.domain.services.sale;

import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;

/**
 * Created by jjh on 1/22/17.
 */
public interface ISaleDomainService {
    ApiResult sell1Card(Sale1CardInput input);
}
