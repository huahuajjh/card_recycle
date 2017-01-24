package com.tqmars.cardrecycle.application.order;

import com.tqmars.cardrecycle.application.order.dto.QueryOrderListInput;

/**
 * Created by jjh on 1/20/17.
 */
public interface IOrderAppService {
    String queryOrderList(QueryOrderListInput input);

}
