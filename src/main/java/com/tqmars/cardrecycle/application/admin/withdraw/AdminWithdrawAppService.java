package com.tqmars.cardrecycle.application.admin.withdraw;

import com.tqmars.cardrecycle.application.admin.withdraw.dto.DealwithWithdrawApplyInput;
import com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordInput;
import com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordOutput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecord;
import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecordDetails;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordDetailsRepository;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordRepository;
import com.tqmars.cardrecycle.domain.services.withdraw.IWithdrawDomainService;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/19/17.
 */
public class AdminWithdrawAppService extends BaseAppService implements IAdminWithdrawAppService{
    private IWithdrawRecordDetailsRepository _withdrawRecordDetailsRepository;
    private IWithdrawDomainService _withdrawDomainService;
    private IWithdrawRecordRepository _withdrawRecordRepository;

    public AdminWithdrawAppService(IWithdrawRecordDetailsRepository _withdrawRecordDetailsRepository,
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
        StringBuilder sb = new StringBuilder("0=0 ");
        if(null != input.getAccount()){
            sb.append("and account='"+input.getAccount()+"'");
        }

        if(null != input.getStatus()){
            sb.append(" and process_status="+input.getStatus());
        }

        sb.append(" limit "+(input.getIndex()-1)*input.getCount()).append(",").append(input.getCount());
        List<WithdrawRecordDetails> list = _withdrawRecordDetailsRepository.getAllWithCondition(sb.toString());
        int count = _withdrawRecordDetailsRepository.countWithCondition(sb.toString());
        _withdrawRecordDetailsRepository.commit();
        return toJsonWithPageFormatter(AutoMapper.mapping(QueryWithdrawRecordOutput.class,list),"success",Code.SUCCESS,count);


    }

    @Override
    public String dealwithWithdrawApply(DealwithWithdrawApplyInput input) {
        WithdrawRecord record = _withdrawRecordRepository.get(input.getWithdrawRecordId());
        record.setProcessMsg(input.getProcessMsg());
        record.setProcessStatus(input.getStatus());
        _withdrawDomainService.dealwithWithdraw(record);
        return toJsonWithFormatter(null,"success",Code.SUCCESS);
    }
}
