package com.tqmars.cardrecycle.webapi.controller.api;

import com.tqmars.cardrecycle.application.api.IApiAppService;
import com.tqmars.cardrecycle.application.api.dto.SellCardInput;
import com.tqmars.cardrecycle.application.api.dto.SellCardOutput;
import com.tqmars.cardrecycle.application.net.HttpClientTool;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jjh on 2/7/17.
 */
@RestController
@RequestMapping(value = "/api",method = {RequestMethod.POST,RequestMethod.GET})
public class ApiController extends ControllerBase {
    IApiAppService service;

    public ApiController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.service = getService("ApiAppService",IApiAppService.class);
    }

    @RequestMapping(value = "/sell")
    public String sell(@RequestParam(value = "cardInfo") String cardInfo){
        SellCardInput input = Serialization.toObject(cardInfo, SellCardInput.class);

        SellCardOutput output = new SellCardOutput();

        if(null == input){
            output.setApiOrderNo(null);
            output.setBusinessOrderNo(input.getBusinessOrderNo());
            output.setMsg("请求参数错误");
            output.setStatus(ApiStatus.PARAM_ERR);
            return Serialization.toJson(output);
        }

        SellCardOutput r = service.sellCard(input);
        Map<String,String> map = new HashMap<>();
        map.put("result",Serialization.toJson(r));
        HttpClientTool.get(input.getCallbackUrl(),map);

        return Serialization.toJson(r);
    }

}
