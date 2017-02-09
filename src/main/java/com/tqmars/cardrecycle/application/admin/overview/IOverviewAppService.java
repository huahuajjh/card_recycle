package com.tqmars.cardrecycle.application.admin.overview;

import com.tqmars.cardrecycle.application.admin.overview.dto.QueryAdminOverviewOutput;

/**
 * Created by jjh on 2/8/17.
 */
public interface IOverviewAppService {
    QueryAdminOverviewOutput queryAdminOverview(String token);
}
