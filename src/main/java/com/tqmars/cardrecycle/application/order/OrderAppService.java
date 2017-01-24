package com.tqmars.cardrecycle.application.order;

import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.application.order.dto.QueryOrderListOutput;
import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/21/17.
 */
public class OrderAppService extends BaseAppService implements IOrderAppService {
    IRepository<OrderDetails,Integer> _orderDetailsRepository;

    public OrderAppService(IRepository<OrderDetails, Integer> _orderDetailsRepository) {
        this._orderDetailsRepository = _orderDetailsRepository;

        this._orderDetailsRepository.setEntityClass(OrderDetails.class);
    }

    @Override
    public String queryOrderList(QueryOrderListInput input) {
        StringBuilder sb = new StringBuilder("1=1 ");
        if(null != input.getOrderNo()){
            sb.append("and order_number='").append(input.getOrderNo()+"'");
            OrderDetails o = _orderDetailsRepository.single(sb.toString());
            int count = _orderDetailsRepository.countWithCondition(sb.toString());
            return toJsonWithPageFormatter(AutoMapper.mapping(QueryOrderListOutput.class,o),"success", Code.SUCCESS,count);

        }else if(null != input.getCardNo()){
            sb.append("and card_number='").append(input.getOrderNo()+"'");
            OrderDetails o = _orderDetailsRepository.single(sb.toString());
            int count = _orderDetailsRepository.countWithCondition(sb.toString());
            return toJsonWithPageFormatter(AutoMapper.mapping(QueryOrderListOutput.class,o),"success", Code.SUCCESS,count);

        }else if(null != input.getCardTypeId() && 0 != input.getCardTypeId()){
            sb.append("and tb_rechargeable_card_type_id="+input.getCardTypeId());

        }else if(-1 != input.getOrderStatus()){
            sb.append(" and ").append("order_status="+input.getOrderStatus());
        }else if(null != input.getFrom() && null != input.getTo()){
            sb.append(" and ").append("order_time between ").
                    append("'").append(input.getFrom()).append("' ").
                    append("and '").append(input.getTo()+"'");
        }

        List<OrderDetails> list = _orderDetailsRepository.getAllWithCondition(sb.toString());
        int count = _orderDetailsRepository.countWithCondition(sb.toString());
        return toJsonWithPageFormatter(AutoMapper.mapping(QueryOrderListOutput.class,list),"success",Code.SUCCESS,count);
    }
}
