package com.tqmars.cardrecycle.webapi.controller.usersale;

import com.google.gson.reflect.TypeToken;
import com.tqmars.cardrecycle.application.sale.ISaleAppService;
import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jjh on 1/16/17.
 */
@RestController
@RequestMapping(value = "/sale",method = RequestMethod.POST)
public class UserSaleController extends ControllerBase {
    private ISaleAppService service;

    public UserSaleController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.service = getService("SaleAppService",ISaleAppService.class);
    }

    @RequestMapping(value = "/sale")
    public String sale(@RequestParam(value = "saleInfo") String saleInfo){
        List<Sale1CardInput> list = Serialization.toList(saleInfo, new TypeToken<List<Sale1CardInput>>(){}.getType());
        if(null == list || list.size() == 0){
            return toJsonWithFormatter(null,"参数错误",Code.ARGUMENT_ERR);
        }

        service.saleListCard(list);
        return toJsonWithFormatter(null,"success", Code.SUCCESS);
    }

}
