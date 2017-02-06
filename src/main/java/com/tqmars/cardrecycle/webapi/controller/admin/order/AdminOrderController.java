package com.tqmars.cardrecycle.webapi.controller.admin.order;

import com.tqmars.cardrecycle.application.admin.order.IAdminOrderAppService;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListOutput;
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
@RequestMapping(value = "/admin/order",method = RequestMethod.POST)
public class AdminOrderController extends ControllerBase {
    private IAdminOrderAppService _adminOrderAppService;

    public AdminOrderController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        this._adminOrderAppService = getService("AdminOrderAppService",IAdminOrderAppService.class);
    }

    /**
     * 查询订单
     * @param url -- /admin/order/query
     * @param condition -- QueryOrderListInput -- {orderNum(订单号),cardNum(充值卡号),orderStatus(订单状态,0处理中,1成功,2失败),createFrom(下单初始时间),createTo(下单截至时间)}
     * @return QueryOrderListOutput -- [{id,orderTime(下单时间),orderNum(订单号),orderStatus(订单状态),name,tel,cardNum(充值卡号),idCardNum(身份证),cardAmount(充值卡面值),saleRatio(比例)}]
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(value = "condition") String condition){
        QueryOrderListInput input = Serialization.toObject(condition, QueryOrderListInput.class);
        String r = _adminOrderAppService.queryOrderList(input);
        return r;
    }

}
