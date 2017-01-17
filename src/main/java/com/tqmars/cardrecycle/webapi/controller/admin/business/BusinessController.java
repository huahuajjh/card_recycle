package com.tqmars.cardrecycle.webapi.controller.admin.business;

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
@RequestMapping(value = "/admin/business",method = RequestMethod.POST)
public class BusinessController extends ControllerBase {
    public BusinessController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        return "";
    }

    @RequestMapping(value = "/saleInfo/query")
    public String querySaleInfo(@RequestParam(value = "condition") String condition){
        return "";
    }


}
