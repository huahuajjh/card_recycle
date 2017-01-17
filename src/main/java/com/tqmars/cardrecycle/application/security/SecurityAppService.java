package com.tqmars.cardrecycle.application.security;

import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.security.dto.ChangeWithdrawPwdInput;
import com.tqmars.cardrecycle.application.security.dto.RealNameAuthInput;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;

/**
 * Created by jjh on 1/16/17.
 */
public class SecurityAppService extends BaseAppService implements ISecurityAppService {
    IUserRepository _userRepository;

    public SecurityAppService(IUserRepository _userRepository) {
        this._userRepository = _userRepository;
        this._userRepository.setEntityClass(User.class);
    }

    @Override
    public boolean changeWithdrawPwd(ChangeWithdrawPwdInput input) {
        User user = _userRepository.get(input.getId());
        user.changWithdrawPwd(input.getNewPwd());

        try {
            _userRepository.update(user);
            _userRepository.commit();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public boolean realNameAuth(RealNameAuthInput input) {
        User user = _userRepository.get(input.getId());
        user.realNameAuth(input.getName(), input.getIdNum());
        try {
            _userRepository.update(user);
            _userRepository.commit();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
