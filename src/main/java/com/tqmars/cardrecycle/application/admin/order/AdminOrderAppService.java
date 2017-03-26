package com.tqmars.cardrecycle.application.admin.order;

import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListAsListOutput;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListOutput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.domain.repositories.IOrderDetailsRepository;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/19/17.
 */
public class AdminOrderAppService extends BaseAppService implements IAdminOrderAppService {
    IOrderDetailsRepository _orderDetailRepository;

    public AdminOrderAppService(IOrderDetailsRepository _orderDetailRepository) {
        this._orderDetailRepository = _orderDetailRepository;
        this._orderDetailRepository.setEntityClass(OrderDetails.class);
    }

    @Override
    public String queryOrderList(QueryOrderListInput input) {
        StringBuilder sb = new StringBuilder(" 0=0 ");

        if(null != input.getOrderNum()){
            sb.append(" and ").append(" order_number='").append(input.getOrderNum()+"'");
        }

        if(null != input.getCardNum()){
            sb.append(" and card_number=").append("'").append(input.getCardNum()).append("'");
        }

        if(null != input.getFrom() && null != input.getTo()){
            sb.append(" and order_time")
                    .append(" ")
                    .append("BETWEEN")
                    .append(" ")
                    .append("'")
                    .append(input.getFrom())
                    .append("'")
                    .append(" AND")
                    .append(" '")
                    .append(input.getTo())
                    .append("'");
        }

        if(null != input.getOrderStatus()){
            sb.append(" and order_status="+input.getOrderStatus());
        }

        int count = _orderDetailRepository.countWithCondition(sb.toString());

        sb.append(" limit ").append((input.getIndex()-1) * input.getCount()).append(",").append(input.getCount());

        List<OrderDetails> list = _orderDetailRepository.getAllWithCondition(sb.toString());

        _orderDetailRepository.commit();
        return toJsonWithPageFormatter(AutoMapper.mapping(QueryOrderListOutput.class,list),"success",Code.SUCCESS,count);
    }

    @Override
    public List<QueryOrderListAsListOutput> queryOrderListAsList(QueryOrderListInput input) {
        if(null == input){
            return null;
        }

        StringBuilder sb = new StringBuilder(" 0=0 ");

        if(null != input.getFrom() && null != input.getTo()){
            sb.append(" and order_time")
                    .append(" ")
                    .append("BETWEEN")
                    .append(" ")
                    .append("'")
                    .append(input.getFrom())
                    .append("'")
                    .append(" AND")
                    .append(" '")
                    .append(input.getTo())
                    .append("'");
        }

        if(null != input.getOrderStatus()){
            sb.append(" and order_status="+input.getOrderStatus());
        }

        List<OrderDetails> list = _orderDetailRepository.getAllWithCondition(sb.toString());

        _orderDetailRepository.commit();
        return AutoMapper.mapping(QueryOrderListAsListOutput.class,list);
    }
}
