package com.tqmars.cardrecycle.application.api;

import com.tqmars.cardrecycle.application.api.dto.SellCardInput;
import com.tqmars.cardrecycle.application.api.dto.SellCardOutput;

/**
 * Created by jjh on 2/7/17.
 */
public interface IApiAppService {
    SellCardOutput sellCard(SellCardInput input);
}
