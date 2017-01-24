package com.tqmars.cardrecycle.application.withdraw;

import com.tqmars.cardrecycle.application.withdraw.dto.ApplyWithdrawInput;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordInput;

/**
 * Created by jjh on 1/22/17.
 */
public interface IWithdrawAppService {
    String queryWithdrawRecord(QueryWithdrawRecordInput input);
    String applyWithdraw(ApplyWithdrawInput input);

}
