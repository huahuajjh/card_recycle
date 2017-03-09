package com.tqmars.cardrecycle.webapi.controller.sale;

import com.google.gson.reflect.TypeToken;
import com.tqmars.cardrecycle.application.sale.ISaleAppService;
import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjh on 1/24/17.
 */
@RestController
@RequestMapping(value = "/sale",method = {RequestMethod.POST,RequestMethod.GET})
public class SaleController extends ControllerBase {
    private ISaleAppService _saleAppService;

    public SaleController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this._saleAppService = getService("SaleAppService",ISaleAppService.class);
    }

    /**
     * 寄售充值卡接口
     * @param -- url -- /sale/sale1card
     * @param input -- Sale1CardInput -- {cardNum(卡号),cardPwd(卡密码),cardCode(卡代码),userId(用户id),cardTypeId(卡类型id),cardItemId(卡item id)}
     * @return void
     */
    @RequestMapping(value = "/sale1card")
    public String sale1card(@RequestParam(value = "input") String input){
        List<Sale1CardInput> list = new ArrayList<>();
        try {
            list = Serialization.toList(input,new TypeToken<List<Sale1CardInput>>(){}.getType());
        }catch (Exception ex){
            return toFailMsg("序列化失败,参数错误");
        }

        if(null == list || list.size() == 0){
            return toFailMsg("参数错误");
        }

        return toSucessMsg();
    }
}
