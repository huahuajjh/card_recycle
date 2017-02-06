package com.tqmars.cardrecycle.application.callback;

import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.callback.dto.OrderCallbackInput;
import com.tqmars.cardrecycle.domain.services.callback.ICallbackDomainService;

/**
 * Created by jjh on 2/6/17.
 */
public class CallbackAppService extends BaseAppService implements ICallbackAppService {
    ICallbackDomainService callbackDomainService;

    public CallbackAppService(ICallbackDomainService callbackDomainService) {
        this.callbackDomainService = callbackDomainService;
    }

    @Override
    public void orderCallbank(OrderCallbackInput input) {
        callbackDomainService.dealOrderCallback(input);
    }
}
