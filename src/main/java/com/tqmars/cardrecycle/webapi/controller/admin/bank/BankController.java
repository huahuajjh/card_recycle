package com.tqmars.cardrecycle.webapi.controller.admin.bank;

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
@RequestMapping(value = "/admin/bank",method = RequestMethod.POST)
public class BankController extends ControllerBase {
    public BankController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        return "";
    }

    @RequestMapping(value = "/modify")
    public String modify(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/del")
    public String del(@RequestParam(value = "input") String input){
        return "";
    }

    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "input") String input){
        return "";
    }

}
