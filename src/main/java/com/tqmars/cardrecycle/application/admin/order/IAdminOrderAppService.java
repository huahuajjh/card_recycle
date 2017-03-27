package com.tqmars.cardrecycle.application.admin.order;

import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListAsListOutput;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;

import java.util.List;

/**
 * Created by jjh on 1/18/17.
 */
public interface IAdminOrderAppService {
    String queryOrderList(QueryOrderListInput input);

    List<QueryOrderListAsListOutput> queryOrderListAsList(QueryOrderListInput input);

}
