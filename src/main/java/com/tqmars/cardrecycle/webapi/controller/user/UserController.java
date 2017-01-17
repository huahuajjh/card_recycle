package com.tqmars.cardrecycle.webapi.controller.user;

import com.tqmars.cardrecycle.application.User.IUserAppService;
import com.tqmars.cardrecycle.application.User.dto.CreateUserInput;
import com.tqmars.cardrecycle.application.User.dto.LoginInput;
import com.tqmars.cardrecycle.application.User.dto.LogoutInput;
import com.tqmars.cardrecycle.application.net.Sms;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.infrastructure.vcode.VCodeGenerator;
import com.tqmars.cardrecycle.webapi.Const;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jjh on 1/14/17.
 */
@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController extends ControllerBase{
    IUserAppService _userAppService;

    public UserController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        _userAppService = getService("UserAppService", IUserAppService.class);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam(value = "reg") String reg){
        CreateUserInput input = Serialization.toObject(reg,CreateUserInput.class);
        if(input == null){
            return toFailMsg("注册信息错误");
        }

        if(!input.getSmsCode().equals(getSession().getAttribute(Const.SMSCODE))){
            return toFailMsg("短信验证码错误",Code.EXTERNAL_ERR);
        }

        _userAppService.register(input);
        return toSuccessMsg("注册成功",Code.SUCCESS);
    }

    @RequestMapping(value = "/getSms")
    public void getSms(@RequestParam(value = "phone") String phone){
        try {
            String smsCode = Sms.sendMsg(phone);
            request.getSession().setAttribute(Const.SMSCODE,smsCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "user") String user) {
        LoginInput input = Serialization.toObject(user,LoginInput.class);
        if(input == null){
            return toFailMsg("登录信息有误",Code.EXTERNAL_ERR);
        }

        if(!input.getVcode().equals(getSession().getAttribute(Const.VCODE))){
            return toFailMsg("验证码错误",Code.VCODE_ERR);
        }

        String token = _userAppService.login(input);
        if(token == null || token.equals("")){
            return toFailMsg("用户名或者密码错误",Code.USER_OR_PWD_ERR);
        }

        getSession().setAttribute(Const.TOKEN,token);

        return toJsonWithFormatter(token,"登录成功",Code.SUCCESS);
    }

    @RequestMapping(value = "/getVCode")
    public void getVCode(){
        VCodeGenerator.CodeObj codeObj = VCodeGenerator.getVCode(100,30);
        getSession().setAttribute(Const.VCODE,codeObj.getvCode());
        try {
            ImageIO.write(codeObj.getImg(),"jpeg",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/change")
    public String changePwd(@RequestParam(value = "user") String user){
        return "";
    }

    @RequestMapping(value = "/logout")
    public String logout(@RequestParam(value = "exitinfo") String user){
        LogoutInput input = Serialization.toObject(user,LogoutInput.class);
        getSession().invalidate();
        _userAppService.logout(input);
        return toSucessMsg();
    }


}
