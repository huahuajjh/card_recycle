package com.tqmars.cardrecycle.application.bank;

import com.tqmars.cardrecycle.application.bank.dto.AddBankAccountInput;
import com.tqmars.cardrecycle.application.bank.dto.ModifyBankAccountInput;
import com.tqmars.cardrecycle.application.bank.dto.QueryBankAccountOutput;

import java.util.List;

/**
 * Created by jjh on 1/22/17.
 */
public interface IBankAppservice {
    List<QueryBankAccountOutput> queryBankAccount();
    void delBankAccount(Integer id);
    void modifyBankAccount(ModifyBankAccountInput input);
    void addBankAccount(AddBankAccountInput input);
    QueryBankAccountOutput queryBankAccountById(Integer id);
}
