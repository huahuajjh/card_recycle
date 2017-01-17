package com.tqmars.cardrecycle.webapi.controller.security;

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
@RequestMapping(value = "/security",method = RequestMethod.POST)
public class SecurityController extends ControllerBase {
    public SecurityController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/realNameAuth")
    public String realNameAuth(@RequestParam(value = "uinfo") String uinfo){
        return "";
    }

    @RequestMapping(value = "/changeWithDrawPwd")
    public String changeWithDrawPwd(@RequestParam(value = "uinfo") String uinfo){
        return "";
    }

}
