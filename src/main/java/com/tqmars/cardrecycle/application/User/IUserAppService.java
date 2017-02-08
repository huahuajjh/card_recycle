package com.tqmars.cardrecycle.application.User;

import com.tqmars.cardrecycle.application.User.dto.*;

/**
 * Created by jjh on 1/14/17.
 */
public interface IUserAppService {
    LoginOutput login(LoginInput input);
    void register(CreateUserInput input);
    boolean changePwd(ChangePwdInput input);
    void logout(LogoutInput input);
    String forgetPwd(ForgetPwdInput input);
    void changeTel(ChangeTelInput input);
}
