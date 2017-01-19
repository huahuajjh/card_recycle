package com.tqmars.cardrecycle.application.admin.order;

import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListOutput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.domain.repositories.IRepositoryOfIntPrimaryKey;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;

import java.util.List;

/**
 * Created by jjh on 1/19/17.
 */
public class AdminOrderAppService extends BaseAppService implements IAdminOrderAppService {
    IRepository<OrderDetails,Integer> _orderDetailRepository;

    public AdminOrderAppService(IRepository<OrderDetails,Integer> _orderDetailRepository) {
        this._orderDetailRepository = _orderDetailRepository;
        this._orderDetailRepository.setEntityClass(OrderDetails.class);
    }

    @Override
    public String queryOrderList(QueryOrderListInput input) {
        StringBuilder sb = new StringBuilder();
        if(null != input.getCardNum()){
            OrderDetails orderDetails = _orderDetailRepository.single("order_number='"+input.getOrderNum()+"'");
            _orderDetailRepository.commit();
            return toJsonWithFormatter(AutoMapper.mapping(QueryOrderListOutput.class, orderDetails),"success", Code.SUCCESS);
        }

        if(null != input.getCardNum()){
            sb.append("card_number=").append("'").append(input.getCardNum()).append("'");
        }else if(null != input.getCreateFrom()){
            sb.append("order_time")
                    .append(" ")
                    .append("BETWEEN")
                    .append(" ")
                    .append("'")
                    .append(input.getCreateFrom())
                    .append("'")
                    .append(" AND")
                    .append(" '")
                    .append(input.getCreateTo())
                    .append("'");
        }

        List<OrderDetails> list = _orderDetailRepository.getAllWithCondition(sb.toString());
        int count = _orderDetailRepository.countWithCondition(sb.toString());
        _orderDetailRepository.commit();
        return toJsonWithPageFormatter(AutoMapper.mapping(QueryOrderListOutput.class,list),"success",Code.SUCCESS,count);
    }
}
