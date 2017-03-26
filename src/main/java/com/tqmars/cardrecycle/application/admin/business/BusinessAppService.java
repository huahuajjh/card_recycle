package com.tqmars.cardrecycle.application.admin.business;

import com.tqmars.cardrecycle.application.admin.business.dto.QueryBusinessListInput;
import com.tqmars.cardrecycle.application.admin.business.dto.QueryBusinessListOutput;
import com.tqmars.cardrecycle.application.admin.business.dto.QueryMerchantAsListOutput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/17/17.
 */
public class BusinessAppService extends BaseAppService implements IBusinessAppService{
    IUserRepository _userRepository;

    public BusinessAppService(IUserRepository _userRepository) {
        this._userRepository = _userRepository;

        this._userRepository.setEntityClass(User.class);
    }

    @Override
    public String queryBusinessList(QueryBusinessListInput input) {
        String condition = "account like '%"+input.getAccount()+"%' limit "+(input.getIndex()-1)*input.getCount()+","+input.getCount();
        List<User> list = _userRepository.getAllWithCondition(condition);
        List<QueryBusinessListOutput> businessList = AutoMapper.mapping(QueryBusinessListOutput.class, list);

        int count = _userRepository.countWithCondition(condition);
        _userRepository.commit();
        return toJsonWithPageFormatter(businessList, "success", Code.SUCCESS,count);
    }

    @Override
    public List<QueryMerchantAsListOutput> queryMerchantAsList() {
        List<User> list = _userRepository.getAll();
        List<QueryMerchantAsListOutput> businessList = AutoMapper.mapping(QueryMerchantAsListOutput.class, list);

        _userRepository.commit();
        return businessList;
    }

}
