package com.tqmars.cardrecycle.webapi.controller.security;

import com.tqmars.cardrecycle.application.security.ISecurityAppService;
import com.tqmars.cardrecycle.application.security.dto.ChangeWithdrawPwdInput;
import com.tqmars.cardrecycle.application.security.dto.RealNameAuthInput;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 1/16/17.
 */
@RestController
@RequestMapping(value = "/security",method = {RequestMethod.POST,RequestMethod.GET})
public class SecurityController extends ControllerBase {
    private ISecurityAppService _securityAppService;

    public SecurityController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this._securityAppService = getService("SecurityAppService",ISecurityAppService.class);
    }

    /**
     * 实名认证
     * @param url -- /security/realNameAuth
     * @param uinfo -- {idNum(身份证),name(姓名),token,id(用户id)}
     * @return void
     */
    @RequestMapping(value = "/realNameAuth")
    public String realNameAuth(@RequestParam(value = "uinfo") String uinfo){
        RealNameAuthInput _uinfo = Serialization.toObject(uinfo, RealNameAuthInput.class);
        boolean r = _securityAppService.realNameAuth(_uinfo);

        if(r){
            return toSucessMsg();
        }else {
            return toFailMsg("认证失败");
        }

    }

    /**
     * 修改提现密码
     * @param url -- /security/changeWithDrawPwd
     * @param uinfo -- {newPwd,token,id}
     * @return void
     */
    @RequestMapping(value = "/changeWithDrawPwd")
    public String changeWithDrawPwd(@RequestParam(value = "uinfo") String uinfo){
        ChangeWithdrawPwdInput _input = Serialization.toObject(uinfo, ChangeWithdrawPwdInput.class);
        boolean r = _securityAppService.changeWithdrawPwd(_input);
        if(r){
            return toSucessMsg();
        }else {
            return toFailMsg("修改密码失败");
        }
    }

}
