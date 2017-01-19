package com.tqmars.cardrecycle.application.admin.business;

import com.tqmars.cardrecycle.application.admin.business.dto.QueryBusinessListInput;

/**
 * Created by jjh on 1/17/17.
 */
public interface IBusinessAppService {
    String queryBusinessList(QueryBusinessListInput input);

}
