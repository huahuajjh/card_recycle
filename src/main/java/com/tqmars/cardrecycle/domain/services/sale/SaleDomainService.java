package com.tqmars.cardrecycle.domain.services.sale;

import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.entities.data.Order;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCard;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardType;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardTypeItem;
import com.tqmars.cardrecycle.domain.repositories.ICardRepository;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeItemRepository;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeRepository;
import com.tqmars.cardrecycle.domain.repositories.IOrderRepository;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.SaleCardApi;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.OrderNumGenerator;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/23/17.
 */
public class SaleDomainService implements ISaleDomainService {
    private IOrderRepository _orderRepository;
    private ICardTypeItemRepository _cardItemRepository;
    private ICardTypeRepository _cardTypeRepository;
    private ICardRepository _cardRepository;

    public SaleDomainService(IOrderRepository _orderRepository,
                             ICardTypeItemRepository _cardItemRepository,
                             ICardTypeRepository _cardTypeRepository,
                             ICardRepository _cardRepository) {

        this._orderRepository = _orderRepository;
        this._cardItemRepository = _cardItemRepository;
        this._cardTypeRepository = _cardTypeRepository;
        this._cardRepository = _cardRepository;

        this._orderRepository.setEntityClass(Order.class);
        this._cardItemRepository.setEntityClass(RechargeableCardTypeItem.class);
        this._cardTypeRepository.setEntityClass(RechargeableCardType.class);
        this._cardRepository.setEntityClass(RechargeableCard.class);
    }

    @Override
    public ApiResult sell1Card(Sale1CardInput input) {
        if(null == input){
            throw new RuntimeException("input参数为空");
        }

        //1,查询卡信息
        //2,生成订单号
        //3,发起api请求
        //4,获取json结果
        //5,判断resultCode
        RechargeableCardTypeItem item = _cardItemRepository.get(input.getCardItemId());
        RechargeableCardType cardType = _cardTypeRepository.get(input.getCardTypeId());

        if(null == item || null == cardType){
            throw new RuntimeException("错误的item id或者cardtype id");
        }

        RechargeableCard card = new RechargeableCard();
        card.setCardNum(input.getCardNum());
        card.setUserId(input.getUserId());
        card.setCardItemId(input.getCardItemId());
        card.setCardPwd(input.getCardPwd());
        card.setCardTypeId(input.getCardTypeId());
        card.setSupportAmount(item.getSupportAmount());
        card.setSaleRatio(cardType.getSaleRatio());

        Integer cardId = _cardRepository.insertAndGetId(card);

        Order order = new Order();
        order.setCardNum(input.getCardNum());

        //卡面值*寄售比例
        order.setActualAmount(item.getSupportAmount().multiply(BigDecimal.valueOf(cardType.getSaleRatio())));
        order.setCardId(cardId);
        order.setCardTypeId(input.getCardTypeId());
        order.setCardTypeItemId(input.getCardItemId());
        //产生订单号
        String orderNo = OrderNumGenerator.generateOrderNum();
        order.setOrderNum(orderNo);
        order.setOrderTime(DateTool.getInstance().getNowSqlTime());
        order.setOrderStatus(0);
        order.setProcessTime(DateTool.getInstance().getNowSqlTime());
        order.setUesrId(input.getUserId());
        order.setCompleteTime(DateTool.getInstance().getNowSqlTime());

        //调用第三方售卡接口
        ApiResult result = SaleCardApi.sale1Card(
                cardType.getCardCode(),
                item.getSupportAmount().toString(),
                input.getCardNum(),
                input.getCardPwd(),
                orderNo);
        if(result.getResultCode().equals("1")){
            order.setThirdOrderNo(result.getOrderNo());
            order.setOrderStatus(0);
        }
        try {
            _orderRepository.insert(order);
            _orderRepository.commit();
            return result;
        }catch (Exception e){
            _orderRepository.commit();
            return result;
        }finally {
            _orderRepository.commit();
            return result;
        }
    }
}
