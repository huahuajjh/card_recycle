package com.tqmars.cardrecycle.webapi.controller.CallBack;

import com.tqmars.cardrecycle.application.callback.ICallbackAppService;
import com.tqmars.cardrecycle.application.callback.dto.OrderCallbackInput;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 2/6/17.
 */
@RestController
@RequestMapping(value = "/callback",method = RequestMethod.GET)
public class CallbackController extends ControllerBase{

    ICallbackAppService service;

    public CallbackController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.service = getService("CallbackAppService",ICallbackAppService.class);
    }

    @RequestMapping(value = "/order/callback")
    public String call(@RequestParam(value = "ResultCode") String ResultCode,
                       @RequestParam(value = "OrderNo") String OrderNo,
                       @RequestParam(value = "MerchOrderNo") String MerchOrderNo,
                       @RequestParam(value = "Message") String Message,
                       @RequestParam(value = "Par") String Par,
                       @RequestParam(value = "RealAmount") String RealAmount,
                       @RequestParam(value = "Sign") String Sign){

        System.out.println(ResultCode);
        System.out.println(OrderNo);
        System.out.println(MerchOrderNo);
        System.out.println(Message);
        System.out.println(Par);
        System.out.println(RealAmount);
        System.out.println(Sign);

        OrderCallbackInput input = new OrderCallbackInput(ResultCode,
                OrderNo,
                MerchOrderNo,
                Message,
                Par,
                RealAmount,
                Sign);

        try {
            service.orderCallbank(input);
            return "ok";
        }catch (Exception e){
            return "ok";
        }finally {
            return "ok";
        }

    }
}
