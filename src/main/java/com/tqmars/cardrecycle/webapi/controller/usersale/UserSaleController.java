package com.tqmars.cardrecycle.webapi.controller.usersale;

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
@RequestMapping(value = "/sale",method = RequestMethod.POST)
public class UserSaleController extends ControllerBase {
    public UserSaleController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping(value = "/sale")
    public String sale(@RequestParam(value = "saleInfo") String saleInfo){
        return "";
    }

}
