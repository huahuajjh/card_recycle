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
        if(null == input){
            orderRepository.commit();
            return;
        }

        Order order = orderRepository.single("order_number='" + input.getMerchOrderNo() + "'");

        if (null == order) {
//            System.out.print("order null");
            orderRepository.commit();
            return;
        }

        Wallet wallet = walletRepository.single("tb_user_id=" + order.getUesrId());

        if (null == wallet) {
//            System.out.print("wallet null");
            orderRepository.commit();
            return;
        }

        RechargeableCard card = cardRepository.get(order.getCardId());

        if (null == card) {
//            System.out.print("card null");
            orderRepository.commit();
            return;
        }

        switch (input.getResultCode()) {
            case "1":
                order.setOrderStatus(1);
<<<<<<< HEAD
                order.setThirdMsg("售卖成功-"+input.getMessage());
=======
                order.setThirdMsg("寄售成功-"+input.getMessage());
>>>>>>> 792642cd6508f7f6a3dddd225d7c9d4e85e38d91
                order.setCompleteTime(DateTool.getInstance().getNowSqlTime());

                wallet.setBalance(wallet.getBalance().add(
                        card.getSupportAmount().multiply(new BigDecimal(card.getSaleRatio()))
                ));
                break;

            case "2":
<<<<<<< HEAD
                order.setOrderStatus(2);
                order.setThirdMsg("面值错误-"+input.getMessage());
                order.setCompleteTime(DateTool.getInstance().getNowSqlTime());
=======
                order.setOrderStatus(1);
                order.setThirdMsg("订单成功,面值有误-"+input.getMessage());
                order.setCompleteTime(DateTool.getInstance().getNowSqlTime());

                wallet.setBalance(wallet.getBalance().add(
                        card.getSupportAmount().multiply(new BigDecimal(input.getPar()))
                ));
>>>>>>> 792642cd6508f7f6a3dddd225d7c9d4e85e38d91
                break;

            case "-1":
                order.setOrderStatus(-1);
<<<<<<< HEAD
                order.setThirdMsg("订单失败-"+input.getMessage());
=======
                order.setThirdMsg("生成订单失败-"+input.getMessage());
>>>>>>> 792642cd6508f7f6a3dddd225d7c9d4e85e38d91
                order.setCompleteTime(DateTool.getInstance().getNowSqlTime());
                break;
        }

        walletRepository.update(wallet);
        orderRepository.update(order);
        orderRepository.commit();

    }
}
