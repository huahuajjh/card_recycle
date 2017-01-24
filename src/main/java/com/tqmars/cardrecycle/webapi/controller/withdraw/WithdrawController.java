package com.tqmars.cardrecycle.webapi.controller.withdraw;

import com.tqmars.cardrecycle.application.withdraw.IWithdrawAppService;
import com.tqmars.cardrecycle.application.withdraw.dto.ApplyWithdrawInput;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordInput;
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
@RequestMapping(value = "/withdraw",method = RequestMethod.POST)
public class WithdrawController extends ControllerBase{
    private IWithdrawAppService _withdrawAppService;

    public WithdrawController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this._withdrawAppService = getService("WithDrawAppService",IWithdrawAppService.class);
    }

    /**
     * 查询提现记录
     * @param url -- /withdraw/query
     * @param condition -- QueryWithdrawRecordInput -- {from(申请时间),to(申请时间),processStatus(处理状态0处理中,1成功,2失败)}
     * @return List<QueryWithdrawRecordOutput> -- [{id,name(姓名),withdrawAmount(提现金额),processStatus(处理状态),processTime(处理时间),cardNum(银行卡卡号),applyTime(申请提现时间),serviceCharge(手续费),actualAmoung(实际提现金额),bankName(银行名称)}]
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        QueryWithdrawRecordInput input = Serialization.toObject(condition, QueryWithdrawRecordInput.class);
        return _withdrawAppService.queryWithdrawRecord(input);
    }

    /**
     * 申请提现
     * @param url -- /withdraw/applyWithdraw
     * @param info -- ApplyWithdrawInput -- {withdrawAmount(提现金额),withdrawPwd(提现密码),bankName(银行名称),userId(用户id),bankId(银行类型id)}
     * @return void
     */
    @RequestMapping(value = "/applyWithdraw")
    public String applyWithdraw(@RequestParam(value = "info") String info){
        ApplyWithdrawInput input = Serialization.toObject(info, ApplyWithdrawInput.class);
        return _withdrawAppService.applyWithdraw(input);

    }

}
