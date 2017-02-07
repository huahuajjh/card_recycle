package com.tqmars.cardrecycle.application.api;

import com.tqmars.cardrecycle.application.api.dto.SellCardInput;
import com.tqmars.cardrecycle.application.api.dto.SellCardOutput;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.services.sale.ISaleDomainService;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;

/**
 * Created by jjh on 2/7/17.
 */
public class ApiAppService extends BaseAppService implements IApiAppService {
    ISaleDomainService service;

    public ApiAppService(ISaleDomainService service) {
        this.service = service;
    }

    @Override
    public SellCardOutput sellCard(SellCardInput input) {
        SellCardOutput output = new SellCardOutput();
        ApiResult result = service.sell1CardApi(input);
        output.setStatus(Integer.valueOf(result.getResultCode()));
        output.setMsg(result.getMessage());
        output.setBusinessOrderNo(result.getMerchOrderNo());
        output.setApiOrderNo(result.getOrderNo());

        return output;
    }
}
