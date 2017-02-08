package com.tqmars.cardrecycle.domain.services.callback;

import com.tqmars.cardrecycle.application.callback.dto.OrderCallbackInput;

/**
 * Created by jjh on 2/6/17.
 */
public interface ICallbackDomainService {
    void dealOrderCallback(OrderCallbackInput input);
}
