package com.tqmars.cardrecycle.application.admin.user;

import com.tqmars.cardrecycle.application.admin.user.dto.ChangePwdInput;
import com.tqmars.cardrecycle.application.admin.user.dto.LoginInput;
import com.tqmars.cardrecycle.application.admin.user.dto.LogoutInput;
import com.tqmars.cardrecycle.application.exception.ApplicationServiceException;

/**
 * Created by jjh on 1/16/17.
 */
public interface IAdminUserAppService {
    String login(LoginInput input);
    boolean changePwd(ChangePwdInput input) throws ApplicationServiceException;
    void logout(LogoutInput input);

}
