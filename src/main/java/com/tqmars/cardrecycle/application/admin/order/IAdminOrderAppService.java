package com.tqmars.cardrecycle.application.admin.order;

import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;

/**
 * Created by jjh on 1/18/17.
 */
public interface IAdminOrderAppService {
    String queryOrderList(QueryOrderListInput input);

}
