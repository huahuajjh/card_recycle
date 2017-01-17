package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.User.IUserAppService;
import com.tqmars.cardrecycle.application.User.dto.CreateUserInput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

/**
 * Created by jjh on 1/15/17.
 */
public class TestUserAppService {
    @Test
    public void testResgister(){
        IUserAppService _userAppService = ServiceLocator.getInstance().getService("UserAppService", IUserAppService.class);
        CreateUserInput input = new CreateUserInput();
        input.setTel("18818181818");
        input.setPwd("12345");
        input.setQq("703825525");
        input.setAccount("huahuajjh");
        input.setBusinessId(Md5.md5WithSalt("huahuajjh"));
        input.setBusinessPwd(Md5.md5WithSalt("12345"));

        _userAppService.register(input);
    }
}
