package com.tqmars.cardrecycle.webapi.controller.user;

import com.tqmars.cardrecycle.application.User.IUserAppService;
import com.tqmars.cardrecycle.application.User.dto.*;
import com.tqmars.cardrecycle.application.net.Sms;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
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
@RequestMapping(value = "/user",method = {RequestMethod.POST,RequestMethod.GET})
public class UserController extends ControllerBase{
    IUserAppService _userAppService;

    public UserController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        _userAppService = getService("UserAppService", IUserAppService.class);
    }

    /**
     * 用户注册
     * @param reg -- {account(账户),pwd(密码),qq,tel(电话),smsCode(短信验证码)}
     * @return void
     */
    @RequestMapping(value = "/register")
    public String register(@RequestParam(value = "reg") String reg){
        CreateUserInput input = Serialization.toObject(reg,CreateUserInput.class);
        if(input == null){
            return toFailMsg("注册信息错误");
        }

        if(!input.getSmsCode().equals(getSession().getAttribute(Const.SMSCODE))){
            return toFailMsg("短信验证码错误",Code.EXTERNAL_ERR);
        }

        input.setWithdrawPwd(input.getPwd());
        input.setBusinessId(PropertiesFileTool.readByKey("businessId"));
        input.setBusinessPwd(PropertiesFileTool.readByKey("businessPwd"));
        _userAppService.register(input);
        return toSuccessMsg("注册成功",Code.SUCCESS);
    }

    @RequestMapping(value = "/getSms")
    public String getSms(@RequestParam(value = "phone") String phone){
        try {
            String smsCode = Sms.sendMsg(phone);
            request.getSession().setAttribute(Const.SMSCODE,smsCode);
            System.out.println(smsCode);
            return toJsonWithFormatter(null,"success",Code.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return toJsonWithFormatter(null,"success",Code.FAIL);
        }
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "user") String user) {
        LoginInput input = Serialization.toObject(user,LoginInput.class);
        if(input == null){
            return toFailMsg("登录信息有误",Code.EXTERNAL_ERR);
        }

        if(!input.getVcode().equals(getSession().getAttribute(Const.VCODE))){
            return toFailMsg("验证码错误",Code.VCODE_ERR);
        }

        LoginOutput r = _userAppService.login(input);
        if(r == null){
            return toFailMsg("用户名或者密码错误",Code.USER_OR_PWD_ERR);
        }

        return toJsonWithFormatter(r,"success",Code.SUCCESS);
    }

    @RequestMapping(value = "/getVCode",method = RequestMethod.GET)
    public void getVCode(){        
        VCodeGenerator.CodeObj codeObj = VCodeGenerator.getVCode(100,30);
        getSession().setAttribute(Const.VCODE,codeObj.getvCode());        
        response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
        try {
            ImageIO.write(codeObj.getImg(),"jpeg",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/change")
    public String changePwd(@RequestParam(value = "user") String user){
        ChangePwdInput input = Serialization.toObject(user, ChangePwdInput.class);
        if(null == input){
            return toFailMsg("参数错误");
        }

        String sms = getSession().getAttribute(Const.SMSCODE).toString();

        if(!input.getSmsCode().equals(sms)){
            return toFailMsg("短信验证码错误");
        }
        boolean r = _userAppService.changePwd(input);
        if(r){
            return toSucessMsg();
        } else {
            return toFailMsg("旧密码错误");
        }

    }

    @RequestMapping(value = "/logout")
    public String logout(@RequestParam(value = "exitinfo") String user){
        LogoutInput input = Serialization.toObject(user,LogoutInput.class);
        getSession().invalidate();
        _userAppService.logout(input);
        return toSucessMsg();
    }

    @RequestMapping(value = "/forgetPwd")
    public String forgetPwd(@RequestParam(value = "forgetPwdInput") String forgetPwdInput){
        ForgetPwdInput input = Serialization.toObject(forgetPwdInput, ForgetPwdInput.class);

        String sms = getSession().getAttribute(Const.SMSCODE).toString();
        if(!sms.equals(input.getSmsCode())){
            return toFailMsg("短信验证码错误");
        }

        return toJsonp(_userAppService.forgetPwd(input));
    }

    @RequestMapping(value = "/tel/change")
    public String changeTel(@RequestParam(value = "changeTelInfo") String changeTelInfo) {
        String sms = getSession().getAttribute(Const.SMSCODE).toString();

        ChangeTelInput input = Serialization.toObject(changeTelInfo, ChangeTelInput.class);
        if(!sms.equals(input.getSms())){
            return toJsonWithFormatter(null,"短信验证码错误",Code.FAIL);
        }
        _userAppService.changeTel(input);
        return toJsonWithFormatter(null,"修改成功",Code.SUCCESS);
    }

    @RequestMapping(value = "/lock")
    public String lock(@RequestParam(value = "id") String id){
        Integer _id = Integer.parseInt(id);
        if(null == id){
            return toFailMsg("parameter id error");
        }

        _userAppService.lock(_id);
        return toSucessMsg("success");
    }

    @RequestMapping(value = "/enable")
    public String enable(@RequestParam(value = "id") String id){
        Integer _id = Integer.parseInt(id);
        if(null == id){
            return toFailMsg("parameter id error");
        }

        _userAppService.enable(_id);
        return toSucessMsg("success");
    }

}
