package com.tqmars.cardrecycle.webapi.controller.wallet;

import com.tqmars.cardrecycle.application.wallet.IWalletAppService;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 2/4/17.
 */
@RestController
@RequestMapping(value = "/wallet",method = {RequestMethod.POST,RequestMethod.GET})
public class WalletController extends ControllerBase{
    IWalletAppService service;

    public WalletController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.service = ServiceLocator.getInstance().getService("WalletAppService",IWalletAppService.class);
    }

    @RequestMapping(value = "/balance")
    public String sale(@RequestParam(value = "userId") String userId){
        return toJsonp(service.getBalance(Integer.valueOf(userId)));
    }

}
