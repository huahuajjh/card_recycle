package com.tqmars.cardrecycle.application.admin.bank;

import com.tqmars.cardrecycle.application.admin.bank.dto.*;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.exception.ApplicationServiceException;
import com.tqmars.cardrecycle.domain.entities.data.Bank;
import com.tqmars.cardrecycle.domain.repositories.IBankRepository;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/16/17.
 */
public class BankAppService extends BaseAppService implements IBankAppService{
    private IBankRepository _bankRepository;

    public BankAppService(IBankRepository _bankRepository) {
        this._bankRepository = _bankRepository;
        this._bankRepository.setEntityClass(Bank.class);
    }

    @Override
    public void addBank(AddBankInput input) {
        _bankRepository.insert(AutoMapper.mapping(Bank.class, input));
        _bankRepository.commit();
    }

    @Override
    public void modifyBank(ModifyBankInput input) {
        Bank bank = _bankRepository.get(input.getId());
        if(bank == null){return;}
        bank.modify(AutoMapper.mapping(Bank.class,input));
        _bankRepository.update(bank);
        _bankRepository.commit();
    }

    @Override
    public void delBank(DelBankInput input) {
        _bankRepository.deleteById(input.getId());
        _bankRepository.commit();

    }

    @Override
    public String queryBankWithCondition(QueryBankWithConditionInput input) {
        int index = input.getIndex()*input.getCount()-1;
        List<Bank> list = _bankRepository.getAllWithCondition("limit "+index+","+input.getCount());
        _bankRepository.commit();
        return toJsonWithPageFormatter(list,"查询成功", Code.SUCCESS,_bankRepository.count());
    }
}
