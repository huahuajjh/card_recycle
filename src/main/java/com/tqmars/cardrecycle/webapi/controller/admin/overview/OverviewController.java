package com.tqmars.cardrecycle.webapi.controller.admin.overview;

import com.tqmars.cardrecycle.application.admin.overview.IOverviewAppService;
import com.tqmars.cardrecycle.application.admin.overview.dto.QueryAdminOverviewOutput;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.webapi.Const;
import com.tqmars.cardrecycle.webapi.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jjh on 2/9/17.
 */
@RestController
@RequestMapping(value = "/admin/overview",method = {RequestMethod.POST,RequestMethod.GET})
public class OverviewController extends ControllerBase {
    IOverviewAppService service;

    public OverviewController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.service = getService("AdminOverviewAppService",IOverviewAppService.class);
    }

    @RequestMapping(value = "query")
    public String query(@RequestParam(value = "token") String token){
        QueryAdminOverviewOutput o = service.queryAdminOverview(token);
        return toJsonWithFormatter(o,"success", Code.SUCCESS);
    }
}
