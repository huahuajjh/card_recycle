package com.tqmars.cardrecycle.application.admin.business;

import com.tqmars.cardrecycle.application.admin.business.dto.QueryBusinessListInput;
import com.tqmars.cardrecycle.application.admin.business.dto.QueryBusinessListOutput;
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
        String condition = "account='"+input.getAccount()+"'";
        List<User> list = _userRepository.getAllWithCondition(condition);
        List<QueryBusinessListOutput> businessList = AutoMapper.mapping(QueryBusinessListOutput.class, list);

        int count = _userRepository.countWithCondition(condition);

        return toJsonWithPageFormatter(businessList, "success", Code.SUCCESS,count);
    }
}
