package com.tqmars.cardrecycle.application.sale;

import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;

/**
 * Created by jjh on 1/21/17.
 */
public interface ISaleAppService {
    ApiResult sale1Card(Sale1CardInput input);
}
