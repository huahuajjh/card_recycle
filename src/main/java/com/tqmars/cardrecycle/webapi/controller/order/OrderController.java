package com.tqmars.cardrecycle.webapi.controller.order;

import com.tqmars.cardrecycle.application.order.IOrderAppService;
import com.tqmars.cardrecycle.application.order.dto.QueryOrderListInput;
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
@RequestMapping(value = "/order",method = {RequestMethod.GET,RequestMethod.POST})
public class OrderController extends ControllerBase {
    IOrderAppService service;

    public OrderController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.service = getService("OrderAppService",IOrderAppService.class);
    }

    @RequestMapping(value = "/queryList")
    public String queryList(@RequestParam(value = "condition") String condition){
        QueryOrderListInput input = Serialization.toObject(condition, QueryOrderListInput.class);
        return toJsonp(service.queryOrderList(input));
    }

}
