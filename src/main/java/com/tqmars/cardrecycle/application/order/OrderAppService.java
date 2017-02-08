package com.tqmars.cardrecycle.application.order;

import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.application.order.dto.QueryOrderListOutput;
import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.domain.repositories.IOrderDetailsRepository;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/21/17.
 */
public class OrderAppService extends BaseAppService implements IOrderAppService {
    IOrderDetailsRepository _orderDetailsRepository;

    public OrderAppService(IOrderDetailsRepository _orderDetailsRepository) {
        this._orderDetailsRepository = _orderDetailsRepository;

        this._orderDetailsRepository.setEntityClass(OrderDetails.class);
    }

    @Override
    public String queryOrderList(QueryOrderListInput input) {
        StringBuilder sb = new StringBuilder("tb_user_id="+input.getUserId());
        if(null != input.getOrderNo()){
            sb.append("and order_number='").append(input.getOrderNo()+"'");
        }

        if(null != input.getCardNo()){
            sb.append("and card_number='").append(input.getOrderNo()+"'");

        }

        if(null != input.getCardTypeId() && 0 != input.getCardTypeId()){
            sb.append("and tb_rechargeable_card_type_id="+input.getCardTypeId());

        }

        if(null != input.getOrderStatus()){
            sb.append(" and ").append("order_status="+input.getOrderStatus());
        }

        if(null != input.getFrom() && null != input.getTo()){
            sb.append(" and ").append("order_time between ").
                    append("'").append(input.getFrom()).append("' ").
                    append("and '").append(input.getTo()+"'");
        }

        sb.append(" limit ").append((input.getIndex()-1) * input.getCount()).append(",").append(input.getCount());

        List<OrderDetails> list = _orderDetailsRepository.getAllWithCondition(sb.toString());
        int count = _orderDetailsRepository.countWithCondition("tb_user_id="+input.getUserId());
        _orderDetailsRepository.commit();
        return toJsonWithPageFormatter(AutoMapper.mapping(QueryOrderListOutput.class,list),"success",Code.SUCCESS,count);
    }
}
