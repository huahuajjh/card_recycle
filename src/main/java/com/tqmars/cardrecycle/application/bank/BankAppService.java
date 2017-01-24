package com.tqmars.cardrecycle.application.bank;

import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.bank.dto.AddBankAccountInput;
import com.tqmars.cardrecycle.application.bank.dto.ModifyBankAccountInput;
import com.tqmars.cardrecycle.application.bank.dto.QueryBankAccountOutput;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.BankAccount;
import com.tqmars.cardrecycle.domain.repositories.IRepository;

import java.util.List;

/**
 * Created by jjh on 1/22/17.
 */
public class BankAppService extends BaseAppService implements IBankAppservice {
    private IRepository<BankAccount,Integer> _bankAccountRepository;

    public BankAppService(IRepository<BankAccount, Integer> _bankAccountRepository) {
        this._bankAccountRepository = _bankAccountRepository;

        this._bankAccountRepository.setEntityClass(BankAccount.class);
    }

    @Override
    public List<QueryBankAccountOutput> queryBankAccount() {
        List<BankAccount> list = _bankAccountRepository.getAll();
        _bankAccountRepository.commit();
        return AutoMapper.mapping(QueryBankAccountOutput.class,list);
    }

    @Override
    public void delBankAccount(Integer id) {
        _bankAccountRepository.deleteById(id);
        _bankAccountRepository.commit();
    }

    @Override
    public void modifyBankAccount(ModifyBankAccountInput input) {
        BankAccount a = _bankAccountRepository.get(input.getId());
        a.setName(input.getName());
        a.setBankId(input.getBankId());
        a.setBankName(input.getBankName());
        a.setCardNum(input.getCardNum());
        _bankAccountRepository.update(a);
        _bankAccountRepository.commit();
    }

    @Override
    public void addBankAccount(AddBankAccountInput input) {
        BankAccount a = AutoMapper.mapping(BankAccount.class, input);
        _bankAccountRepository.insert(a);
        _bankAccountRepository.commit();
    }

    @Override
    public QueryBankAccountOutput queryBankAccountById(Integer id) {
        BankAccount a = _bankAccountRepository.get(id);
        return AutoMapper.mapping(QueryBankAccountOutput.class, a);
    }
}
