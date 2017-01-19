package com.tqmars.cardrecycle.application.admin.withdraw;

import com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordInput;
import com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordOutput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecord;
import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecordDetails;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordRepository;
import com.tqmars.cardrecycle.domain.services.withdraw.IWithdrawDomainService;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/19/17.
 */
public class AdminWithdrawAppService extends BaseAppService implements IAdminWithdrawAppService{
    private IRepository<WithdrawRecordDetails,Integer> _withdrawRecordDetailsRepository;
    private IWithdrawDomainService _withdrawDomainService;
    private IWithdrawRecordRepository _withdrawRecordRepository;

    public AdminWithdrawAppService(IRepository<WithdrawRecordDetails, Integer> _withdrawRecordDetailsRepository,
                                   IWithdrawDomainService _withdrawDomainService,
                                   IWithdrawRecordRepository _withdrawRecordRepository) {
        this._withdrawRecordDetailsRepository = _withdrawRecordDetailsRepository;
        this._withdrawDomainService = _withdrawDomainService;
        this._withdrawRecordRepository = _withdrawRecordRepository;

        this._withdrawRecordDetailsRepository.setEntityClass(WithdrawRecordDetails.class);
        this._withdrawRecordRepository.setEntityClass(WithdrawRecord.class);
    }

    @Override
    public String queryWithdrawRecord(QueryWithdrawRecordInput input) {
        if(null != input.getAccount()){
            WithdrawRecordDetails record = _withdrawRecordDetailsRepository.single("account='"+input.getAccount()+"'");
            _withdrawRecordDetailsRepository.commit();
            return toJsonWithFormatter(AutoMapper.mapping(QueryWithdrawRecordOutput.class,record),"success", Code.SUCCESS);
        }

        List<WithdrawRecordDetails> list = _withdrawRecordDetailsRepository.getAllWithCondition("process_status="+input.getStatus());
        int count = _withdrawRecordDetailsRepository.countWithCondition("process_status="+input.getStatus());
        _withdrawRecordDetailsRepository.commit();
        return toJsonWithPageFormatter(AutoMapper.mapping(QueryWithdrawRecordOutput.class,list),"success",Code.SUCCESS,count);
    }

    @Override
    public String dealwithWithdrawApply(Integer id) {
        WithdrawRecord record = _withdrawRecordRepository.get(id);
        return _withdrawDomainService.dealwithWithdraw(record);
    }
}
