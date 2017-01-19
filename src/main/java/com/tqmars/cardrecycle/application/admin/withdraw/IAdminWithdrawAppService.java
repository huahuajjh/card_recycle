package com.tqmars.cardrecycle.application.admin.withdraw;

import com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordInput;

/**
 * Created by jjh on 1/19/17.
 */
public interface IAdminWithdrawAppService {
    String queryWithdrawRecord(QueryWithdrawRecordInput input);
    String dealwithWithdrawApply(Integer id);

}
