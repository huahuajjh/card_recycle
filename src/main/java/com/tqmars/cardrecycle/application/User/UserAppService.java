package com.tqmars.cardrecycle.application.User;

import com.tqmars.cardrecycle.application.User.dto.*;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.entities.data.Wallet;
import com.tqmars.cardrecycle.domain.repositories.IUserRepository;
import com.tqmars.cardrecycle.domain.repositories.IWalletRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/14/17.
 */
public class UserAppService extends BaseAppService implements IUserAppService{
    private IUserRepository _userRepository;
    private IWalletRepository walletRepository;
//    private IBankAccountRepository _bankAccountRepository;

    public UserAppService(IUserRepository repository,IWalletRepository walletRepository){
        this._userRepository = repository;
        this.walletRepository = walletRepository;

        this._userRepository.setEntityClass(User.class);
        this.walletRepository.setEntityClass(Wallet.class);
    }

    @Override
    public LoginOutput login(LoginInput input) {
        input.setPwd(Md5.md5WithSalt(input.getPwd()));
        String token = _userRepository.login(AutoMapper.mapping(User.class,input));
        User user = _userRepository.single("account='"+input.getAccount()+"'");
        if(null == token || token.equals("") || null == user){
            _userRepository.commit();
            return null;
        }

        user.setToken(token);

        _userRepository.commit();
        LoginOutput output = AutoMapper.mapping(LoginOutput.class,user);
        return output;

    }

    @Override
    public void register(CreateUserInput input) {
        input.setBusinessId(Md5.md5WithSalt(input.getAccount()));
        input.setBusinessPwd(Md5.md5WithSalt(input.getBusinessPwd()));
        input.setPwd(Md5.md5WithSalt(input.getPwd()));
        input.setWithdrawPwd(input.getPwd());

        User u = AutoMapper.mapping(User.class,input);
        u.setLastLoginTime(DateTool.getInstance().getNowSqlTime());
        Integer userId = _userRepository.insertAndGetId(u);

        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("0.00"));
        wallet.setUserId(userId);

        walletRepository.insert(wallet);
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
        User user = _userRepository.single("account='"+input.getAccount()+"' and tel='"+input.getTel()+"'");
        if (null == user){
            return toJsonWithFormatter(null,"不存在的用户名或者手机号", Code.NOT_EXISTS_ACCOUNT);
        }
        user.setPwd(Md5.md5WithSalt(input.getNewPwd()));
        _userRepository.update(user);
        _userRepository.commit();
        return toJsonWithFormatter(null,"success",Code.SUCCESS);
    }

    @Override
    public void changeTel(ChangeTelInput input) {
        User user = _userRepository.get(input.getUserId());
        user.setTel(input.getNewTel());
        _userRepository.update(user);
        _userRepository.commit();
    }

    @Override
    public boolean auth(String token) {
        boolean r = _userRepository.auth(token);
        _userRepository.commit();
        return r;
    }

    @Override
    public void lock(Integer id) {
        User u = _userRepository.get(id);
        if(null == u){
            _userRepository.commit();
            return;
        }
        u.lock();
        _userRepository.commit();
    }

    @Override
    public void enable(Integer id) {
        User u = _userRepository.get(id);
        if(null == u){
            _userRepository.commit();
            return;
        }
        u.enable();
        _userRepository.commit();
    }

}
