package com.tqmars.cardrecycle.application.admin.user;

import com.tqmars.cardrecycle.application.admin.user.dto.*;
import com.tqmars.cardrecycle.application.exception.ApplicationServiceException;

import java.util.List;

/**
 * Created by jjh on 1/16/17.
 */
public interface IAdminUserAppService {
    String login(LoginInput input);
    boolean changePwd(ChangePwdInput input) throws ApplicationServiceException;
    void logout(LogoutInput input);
    void createUser(CreateUserInput input);
    void delUser(Integer id);
    void modifyUser(ModifyUserInput input);
    List<QueryUserListOutput> queryUserList(QueryUserListInput input);

}
