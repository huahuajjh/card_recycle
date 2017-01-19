package com.tqmars.cardrecycle.webapi.controller.admin.business;

import com.tqmars.cardrecycle.application.admin.business.IBusinessAppService;
import com.tqmars.cardrecycle.application.admin.business.dto.QueryBusinessListInput;
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
@RequestMapping(value = "/admin/business",method = RequestMethod.POST)
public class BusinessController extends ControllerBase {
    private IBusinessAppService _businessAppService;

    public BusinessController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        _businessAppService = getService("BusinessAppService",IBusinessAppService.class);
    }

    /**
     * 查询商户信息
     * @param url -- /admin/business/query
     * @param condition -- QueryBusinessListInput -- {account(账户),from(起始日期),to(截止日期)}
     * @return QueryBusinessListOutput -- [{id,account,tel,qq,businessId(商户id),name,idCardNum(身份证)}]
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        QueryBusinessListInput input = Serialization.toObject(condition, QueryBusinessListInput.class);
        return _businessAppService.queryBusinessList(input);
    }


}
