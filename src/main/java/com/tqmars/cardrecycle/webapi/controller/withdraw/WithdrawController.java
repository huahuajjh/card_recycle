package com.tqmars.cardrecycle.webapi.controller.withdraw;

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
@RequestMapping(value = "/withdraw",method = RequestMethod.POST)
public class WithdrawController extends ControllerBase{
    public WithdrawController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        return "";
    }

    @RequestMapping(value = "/bankAccount/add")
    public String addBankAccount(@RequestParam(value = "bankAccount") String bankAccount){
        return "";
    }

    @RequestMapping(value = "/withdraw")
    public String withdraw(@RequestParam(value = "withdrawInfo") String withdrawInfo){
        return "";
    }

    @RequestMapping(value = "/bankAccount/modify")
    public String modifyBankAccont(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/bankAccount/del")
    public String delBankAccont(@RequestParam(value = "input") String input){
        return "";
    }

}
