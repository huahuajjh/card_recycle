package com.tqmars.cardrecycle.application.admin.bank;

import com.tqmars.cardrecycle.application.admin.bank.dto.AddBankInput;
import com.tqmars.cardrecycle.application.admin.bank.dto.BankOutput;
import com.tqmars.cardrecycle.application.admin.bank.dto.ModifyBankInput;
import com.tqmars.cardrecycle.application.admin.bank.dto.QueryBankWithConditionInput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
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
    public String addBank(AddBankInput input) {
        boolean exists = _bankRepository.isExists("name='"+input.getName()+"'");
        if(exists){
            _bankRepository.commit();
            return toJsonWithFormatter(null,"卡行名称"+input.getName()+"已经存在",Code.FAIL);
        }

        _bankRepository.insert(AutoMapper.mapping(Bank.class, input));
        _bankRepository.commit();
        return toSuccessMsg("新增卡行成功");
    }

    @Override
    public void modifyBank(ModifyBankInput input) {
        boolean exists = _bankRepository.isExists("name='"+input.getName()+"'");
        if(exists){
            _bankRepository.commit();
            return;
        }

        Bank bank = _bankRepository.get(input.getId());
        if(bank == null){return;}
        bank.modify(AutoMapper.mapping(Bank.class,input));
        _bankRepository.update(bank);
        _bankRepository.commit();
    }

    @Override
    public void delBank(Integer id) {
        _bankRepository.deleteById(id);
        _bankRepository.commit();
    }

    @Override
    public String queryBankWithCondition(QueryBankWithConditionInput input) {
        int index = input.getIndex()*input.getCount()-1;
        List<Bank> list = _bankRepository.getAllWithCondition("limit "+index+","+input.getCount());
        _bankRepository.commit();
        return toJsonWithPageFormatter(AutoMapper.mapping(BankOutput.class,list),"查询成功", Code.SUCCESS,_bankRepository.count());
    }

    @Override
    public String queryAll() {
        List<Bank> list = _bankRepository.getAll();
        _bankRepository.commit();
        return toJsonWithFormatter(AutoMapper.mapping(BankOutput.class,list),"success",Code.SUCCESS);
    }


}
