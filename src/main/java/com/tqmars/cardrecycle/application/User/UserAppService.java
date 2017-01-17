package com.tqmars.cardrecycle.application.User;

import com.tqmars.cardrecycle.application.User.dto.ChangePwdInput;
import com.tqmars.cardrecycle.application.User.dto.CreateUserInput;
import com.tqmars.cardrecycle.application.User.dto.LoginInput;
import com.tqmars.cardrecycle.application.User.dto.LogoutInput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.BankAccount;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IBankAccountRepository;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;

/**
 * Created by jjh on 1/14/17.
 */
public class UserAppService extends BaseAppService implements IUserAppService{
    private IUserRepository _userRepository;
    private IBankAccountRepository _bankAccountRepository;

    public UserAppService(IUserRepository repository,IBankAccountRepository bankAccountRepository){
        this._userRepository = repository;
        this._bankAccountRepository = bankAccountRepository;

        this._bankAccountRepository.setEntityClass(BankAccount.class);
        this._userRepository.setEntityClass(User.class);
    }

    @Override
    public String login(LoginInput input) {
        input.setPwd(Md5.md5WithSalt(input.getPwd()));
        String token = _userRepository.login(AutoMapper.mapping(User.class,input));
        _userRepository.commit();
        return token;
    }

    @Override
    public void register(CreateUserInput input) {
        input.setBusinessId(Md5.md5WithSalt(input.getAccount()));
        input.setBusinessPwd(Md5.md5WithSalt(input.getBusinessPwd()));
        input.setPwd(Md5.md5WithSalt(input.getPwd()));
        input.setWithdrawPwd(input.getPwd());

        _userRepository.insert(AutoMapper.mapping(User.class,input));
        _userRepository.commit();

    }

    @Override
    public boolean changePwd(ChangePwdInput input) {
        User u = _userRepository.single("pwd='"+Md5.md5WithSalt(input.getOldPwd())+"' and token='"+input.getToken()+"'");
        if(u == null){
            return false;
        }

        u.changPwd(input.getNewPwd());
        _userRepository.update(u);
        _userRepository.commit();
        return true;
    }

    @Override
    public void logout(LogoutInput input) {
        _userRepository.logout(AutoMapper.mapping(User.class,input));
        _userRepository.commit();
    }

}
