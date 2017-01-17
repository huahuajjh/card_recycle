package com.tqmars.cardrecycle.webapi.controller.order;

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
@RequestMapping(value = "/order",method = RequestMethod.POST)
public class OrderController extends ControllerBase {
    public OrderController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @RequestMapping("/query")
    public String query(@RequestParam(value = "condition") String condition){
        return "";
    }

    @RequestMapping("/queryDetails")
    public String queryDetails(@RequestParam(value = "orderId") String orderId){
        return "";
    }

}
