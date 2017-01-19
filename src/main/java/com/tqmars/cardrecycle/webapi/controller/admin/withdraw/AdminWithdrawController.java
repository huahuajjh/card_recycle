package com.tqmars.cardrecycle.webapi.controller.admin.withdraw;

import com.tqmars.cardrecycle.application.admin.withdraw.IAdminWithdrawAppService;
import com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordInput;
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
@RequestMapping(value = "/admin/withdraw",method = RequestMethod.POST)
public class AdminWithdrawController extends ControllerBase{
    private IAdminWithdrawAppService _adminWithdrawAppService;

    public AdminWithdrawController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this._adminWithdrawAppService = getService("AdminWithdrawAppService",IAdminWithdrawAppService.class);
    }

    /**
     * 条件查询提现申请记录
     * @param url -- /admin/withdraw/query
     * @param condition -- QueryWithdrawRecordInput -- {status,account}
     * @return QueryWithdrawRecordOutput -- [{id,businessId(商户id),name,withdrawAmount(提现金额),balance(余额),processStatus(处理状态,0处理中,1成功,2失败),processTime(处理日期),
     * cardNum(充值卡卡号),msg(处理消息),account}]
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        QueryWithdrawRecordInput _input = Serialization.toObject(condition, QueryWithdrawRecordInput.class);
        return  _adminWithdrawAppService.queryWithdrawRecord(_input);
    }

    /**
     * 处理提现申请
     * @param url -- /admin/withdraw/deal
     * @param input -- id(提现记录对象id)
     * @return void
     */
    @RequestMapping(value = "/deal")
    public String deal(@RequestParam(value = "input") String input){
        _adminWithdrawAppService.dealwithWithdrawApply(Integer.valueOf(input));
        return toSucessMsg();
    }

}
