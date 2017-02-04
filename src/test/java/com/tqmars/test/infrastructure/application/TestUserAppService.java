package com.tqmars.test.infrastructure.application;

import com.tqmars.cardrecycle.application.User.IUserAppService;
import com.tqmars.cardrecycle.application.User.dto.ChangePwdInput;
import com.tqmars.cardrecycle.application.User.dto.CreateUserInput;
import com.tqmars.cardrecycle.application.User.dto.LoginInput;
import com.tqmars.cardrecycle.application.User.dto.LogoutInput;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jjh on 1/16/17.
 */
public class TestUserAppService {
    IUserAppService _userAppServicve;

    @Before
    public void init(){
        _userAppServicve = ServiceLocator.getInstance().getService("UserAppService", IUserAppService.class);
    }

    @Test
    public void testLogin(){
        LoginInput input = new LoginInput();
        input.setAccount("huahuajjh");
        input.setPwd("1234");
//        String token = _userAppServicve.login(input);
//        System.out.println(token);
    }

    @Test
    public void testExit(){
        LogoutInput input = new LogoutInput();
        input.setToken("1c320a1eb680643fca0b4d254ebb7e26");
        _userAppServicve.logout(input);
    }

    @Test
    public void testChangePwd(){
        ChangePwdInput input = new ChangePwdInput();
//        input.setToken("1c320a1eb680643fca0b4d254ebb7e26");
        input.setNewPwd("1234");
        input.setOldPwd("12345");

        boolean r = _userAppServicve.changePwd(input);
        System.out.println(r);

    }

    @Test
    public void testRegister(){
        CreateUserInput input = new CreateUserInput();
        input.setAccount("huahuajjh");
        input.setQq("703825021");
        input.setTel("18817676235");
        input.setPwd("huahuajjh3");
        _userAppServicve.register(input);
    }

}
