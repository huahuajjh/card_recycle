package com.tqmars.cardrecycle.domain.services.callback;

import com.tqmars.cardrecycle.application.callback.dto.OrderCallbackInput;
import com.tqmars.cardrecycle.domain.entities.data.Order;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCard;
import com.tqmars.cardrecycle.domain.entities.data.Wallet;
import com.tqmars.cardrecycle.domain.repositories.ICardRepository;
import com.tqmars.cardrecycle.domain.repositories.IOrderRepository;
import com.tqmars.cardrecycle.domain.repositories.IWalletRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;

import java.math.BigDecimal;

/**
 * Created by jjh on 2/6/17.
 */
public class CallbackDomainService implements ICallbackDomainService {
    IOrderRepository orderRepository;
    IWalletRepository walletRepository;
    ICardRepository cardRepository;

    public CallbackDomainService(IOrderRepository orderRepository, IWalletRepository walletRepository, ICardRepository cardRepository) {
        this.orderRepository = orderRepository;
        this.walletRepository = walletRepository;
        this.cardRepository = cardRepository;

        this.orderRepository.setEntityClass(Order.class);
        this.walletRepository.setEntityClass(Wallet.class);
        this.cardRepository.setEntityClass(RechargeableCard.class);
    }

    @Override
    public void dealOrderCallback(OrderCallbackInput input) {
        Order order = orderRepository.single("order_number='"+input.getMerchOrderNo()+"'");

        if(null == order){
            throw new RuntimeException("订单回调接口异常,未查询到订单数据");
        }

        Wallet wallet = walletRepository.single("tb_user_id="+order.getUesrId());

        if(null == wallet){
            throw new RuntimeException("订单回调接口异常,未查询到钱包数据");
        }

        RechargeableCard card = cardRepository.get(order.getCardId());

        if(null == card){
            throw new RuntimeException("订单回调接口异常,未查询到充值卡数据");
        }

        switch (input.getResultCode()){
            case "1":
                order.setOrderStatus(1);
                order.setThirdMsg(input.getMessage());
                order.setCompleteTime(DateTool.getInstance().getNowSqlTime());

                wallet.setBalance(wallet.getBalance().add(
                        card.getSupportAmount().multiply(new BigDecimal(card.getSaleRatio()))
                ));
                break;

            case "2":
                order.setOrderStatus(2);
                order.setThirdMsg(input.getMessage());
                order.setCompleteTime(DateTool.getInstance().getNowSqlTime());
                break;

            case "-1":
                order.setOrderStatus(2);
                order.setThirdMsg(input.getMessage());
                order.setCompleteTime(DateTool.getInstance().getNowSqlTime());
                break;
        }

        try {
            walletRepository.update(wallet);
            orderRepository.update(order);
            orderRepository.commit();
        }catch (Exception e){
            orderRepository.commit();
        }finally {
            orderRepository.commit();
        }



    }
}
