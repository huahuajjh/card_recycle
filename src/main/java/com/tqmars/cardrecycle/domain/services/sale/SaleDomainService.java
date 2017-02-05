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
import com.tqmars.cardrecycle.infrastructure.StringTools.OrderNumGenerator;

import java.math.BigDecimal;
import java.util.Date;

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
    }

    @Override
    public ApiResult sell1Card(Sale1CardInput input) {
        //1,查询卡信息
        //2,生成订单号
        //3,发起api请求
        //4,获取json结果
        //5,判断resultCode
        RechargeableCardTypeItem item = _cardItemRepository.get(input.getCardItemId());
        RechargeableCardType cardType = _cardTypeRepository.get(input.getCardTypeId());

        RechargeableCard card = new RechargeableCard();
        card.setCardNum(input.getCardNum());
        card.setUserId(input.getUserId());
        card.setCardItemId(input.getCardItemId());
        card.setCardPwd(input.getCardPwd());

        Integer cardId = _cardRepository.insertAndGetId(card);

        Order order = new Order();
        order.setCardNum(input.getCardNum());
        order.setActualAmount(item.getSupportAmount().multiply(BigDecimal.valueOf(cardType.getSaleRatio())));
        order.setCardId(cardId);
        order.setCardTypeId(input.getCardTypeId());
        order.setCardTypeItemId(input.getCardItemId());
        String orderNo = OrderNumGenerator.generateOrderNum();
        order.setOrderNum(orderNo);
        order.setOrderTime(new Date());
        order.setOrderStatus(0);
        order.setProcessTime(new Date());
        order.setUesrId(input.getUserId());

        //调用第三方售卡接口
        ApiResult result = SaleCardApi.sale1Card(
                cardType.getCardCode(),
                item.getSupportAmount().toString(),
                input.getCardNum(),
                input.getCardPwd(),
                orderNo);
        if(result.getResultCode().equals("2")){
            order.setThirdOrderNo(result.getOrderNo());
        }
        _orderRepository.insert(order);
        _orderRepository.commit();
        return result;
    }
}
