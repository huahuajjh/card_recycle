package com.tqmars.cardrecycle.application.User;

import com.tqmars.cardrecycle.application.User.dto.*;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

/**
 * Created by jjh on 1/14/17.
 */
public class UserAppService extends BaseAppService implements IUserAppService{
    private IUserRepository _userRepository;
//    private IBankAccountRepository _bankAccountRepository;

    public UserAppService(IUserRepository repository){
        this._userRepository = repository;
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
        System.out.println(input.toString());

        _userRepository.insert(AutoMapper.mapping(User.class,input));
        _userRepository.commit();

    }

    @Override
    public boolean changePwd(ChangePwdInput input) {
        User u = _userRepository.single("pwd='"+Md5.md5WithSalt(input.getOldPwd())+"' and account='"+input.getAccount()+"'");
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

    @Override
    public String forgetPwd(ForgetPwdInput input) {
        User user = _userRepository.single("account='"+input.getAccount()+"'");
        if (null == user){
            return toJsonWithFormatter(null,"不存在的用户名", Code.FAIL);
        }
        user.setPwd(Md5.md5WithSalt(input.getNewPwd()));
        _userRepository.update(user);
        return toJsonWithFormatter(null,"success",Code.SUCCESS);
    }

}
