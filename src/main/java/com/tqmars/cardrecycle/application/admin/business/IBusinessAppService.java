package com.tqmars.cardrecycle.application.admin.business;

import com.tqmars.cardrecycle.application.admin.business.dto.QueryBusinessListInput;
import com.tqmars.cardrecycle.application.admin.business.dto.QueryMerchantAsListOutput;

import java.util.List;

/**
 * Created by jjh on 1/17/17.
 */
public interface IBusinessAppService {
    String queryBusinessList(QueryBusinessListInput input);

    List<QueryMerchantAsListOutput> queryMerchantAsList();

}
