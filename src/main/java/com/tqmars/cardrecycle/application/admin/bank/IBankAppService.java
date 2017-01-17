package com.tqmars.cardrecycle.application.admin.bank;

import com.tqmars.cardrecycle.application.admin.bank.dto.AddBankInput;
import com.tqmars.cardrecycle.application.admin.bank.dto.DelBankInput;
import com.tqmars.cardrecycle.application.admin.bank.dto.ModifyBankInput;
import com.tqmars.cardrecycle.application.admin.bank.dto.QueryBankWithConditionInput;

/**
 * Created by jjh on 1/16/17.
 */
public interface IBankAppService {
    void addBank(AddBankInput input);
    void modifyBank(ModifyBankInput input);
    void delBank(DelBankInput input);
    String queryBankWithCondition(QueryBankWithConditionInput input);

}
