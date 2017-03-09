package com.tqmars.cardrecycle.domain.services.sale;

import com.tqmars.cardrecycle.application.api.dto.SellCardInput;
import com.tqmars.cardrecycle.application.sale.dto.Sale1CardInput;
import com.tqmars.cardrecycle.domain.entities.data.*;
import com.tqmars.cardrecycle.domain.repositories.*;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.SaleCardApi;
import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by jjh on 1/23/17.
 */
public class SaleDomainService implements ISaleDomainService {
    private IOrderRepository _orderRepository;
    private ICardTypeItemRepository _cardItemRepository;
    private ICardTypeRepository _cardTypeRepository;
    private ICardRepository _cardRepository;
    private IUserRepository _userRepository;

    public SaleDomainService(IOrderRepository _orderRepository,
                             ICardTypeItemRepository _cardItemRepository,
                             ICardTypeRepository _cardTypeRepository,
                             ICardRepository _cardRepository,
                             IUserRepository _userRepository) {

        this._orderRepository = _orderRepository;
        this._cardItemRepository = _cardItemRepository;
        this._cardTypeRepository = _cardTypeRepository;
        this._cardRepository = _cardRepository;
        this._userRepository = _userRepository;

        this._orderRepository.setEntityClass(Order.class);
        this._cardItemRepository.setEntityClass(RechargeableCardTypeItem.class);
        this._cardTypeRepository.setEntityClass(RechargeableCardType.class);
        this._cardRepository.setEntityClass(RechargeableCard.class);
        this._userRepository.setEntityClass(User.class);
    }

//    @Async
    @Override
    public ApiResult sell1Card(Sale1CardInput input) {
        ApiResult result = sell(input);
        _orderRepository.commit();
        return result;
    }

//    @Async
    @Override
    public void sellListCard(List<Sale1CardInput> list) {
        try {
            list.forEach(l-> sell(l));
            _orderRepository.commit();
        }catch (Exception ex){
            LoggerFactory.getLogger().error("exception occur in sale card list:"+ex.getMessage(),ex);
        }finally {
            _orderRepository.closeSession();
        }
    }

    @Override
    public ApiResult sell1CardApi(SellCardInput input) {
        if (null == input) {
            throw new RuntimeException("input参数为空");
        }

        Sale1CardInput scp = new Sale1CardInput();
        scp.setCardNum(input.getCardNo());
        scp.setCardPwd(input.getCardPwd());
        scp.setCardCode(input.getCardCode());

        ApiResult result = new ApiResult();

        User user = _userRepository.single("business_id='"+input.getBusinessId()+"' and business_pwd='"+input.getBusinessPwd()+"'");
        if(null == user){
            result.setMerchOrderNo(null);
            result.setMessage("不存在的商户");
            result.setOrderNo(null);
            result.setMerchOrderNo(input.getBusinessOrderNo());
            result.setResultCode(ApiResult.ResultCode.NOT_EXISTS_BUSINESS);
            _userRepository.commit();
            return result;
        }

        RechargeableCardType cardType = _cardTypeRepository.single("card_shortcut='"+input.getCardCode()+"'");
        if(null == cardType){
            result.setMerchOrderNo(null);
            result.setMessage("不存在的卡类型");
            result.setOrderNo(null);
            result.setMerchOrderNo(input.getBusinessOrderNo());
            result.setResultCode(ApiResult.ResultCode.NOT_EXISTS_CARD_TYPE);
            _cardTypeRepository.commit();
            return result;
        }

        RechargeableCardTypeItem cardTypeItem = _cardItemRepository.single("tb_rechargeable_card_type_id="+cardType.getId()+" and support_amount='"+input.getCardAmount()+"'");
        if(null == cardTypeItem){
            result.setMerchOrderNo(null);
            result.setMessage("不支持的面值");
            result.setOrderNo(null);
            result.setMerchOrderNo(input.getBusinessOrderNo());
            result.setResultCode(ApiResult.ResultCode.NOT_EXISTS_CARD_ITEM_AMOUNT);
            _cardItemRepository.commit();
            return result;
        }

        scp.setCardTypeId(cardType.getId());
        scp.setCardItemId(cardTypeItem.getId());
        scp.setUserId(user.getId());
        result = sell1Card(scp);

        return result;
    }

    private void dealResult(Order order, String msg, String resCode, String orderNo) {
        if(null == resCode){
            throw new RuntimeException("第三方接口错误,订单生成失败");
        }

        switch (resCode) {
            case "1":
                order.setThirdOrderNo(orderNo);
                order.setOrderStatus(0);
                order.setThirdMsg("售卡成功,等待处理:" + msg);
                break;

            case "2":
                order.setThirdOrderNo(orderNo);
                order.setOrderStatus(2);
                order.setThirdMsg("售卡失败:" + msg);
                break;

            case "-1":
            case "-3":
            case "-4":
                order.setThirdOrderNo(orderNo);
                order.setOrderStatus(2);
                order.setThirdMsg("错误,参数不正确,请检查卡号和密码是否正确,请勿重复提交:" + msg);
                break;

            case "-2":
                order.setThirdOrderNo(orderNo);
                order.setOrderStatus(2);
                order.setThirdMsg("不存在的商户:" + msg);
                break;

            case "-5":
                order.setThirdOrderNo(orderNo);
                order.setOrderStatus(2);
                order.setThirdMsg("未启用api:" + msg);
                break;

            case "-6":
                order.setThirdOrderNo(orderNo);
                order.setOrderStatus(2);
                order.setThirdMsg("订单生成失败:" + msg);
                break;

            case "-9":
                order.setThirdOrderNo(orderNo);
                order.setOrderStatus(2);
                order.setThirdMsg("订单生成失败,第三方接口错误:" + msg);
                break;
        }

    }

    private ApiResult sell(Sale1CardInput input) {
        if (null == input) {
            throw new RuntimeException("input参数为空");
        }

        RechargeableCardTypeItem item = _cardItemRepository.get(input.getCardItemId());
        RechargeableCardType cardType = _cardTypeRepository.get(input.getCardTypeId());

        if (null == item || null == cardType) {
            throw new RuntimeException("错误的item id或者cardtype id");
        }

        //初始化被寄售卡
        RechargeableCard card = RechargeableCard.createCard(input.getCardNum(), input.getUserId(), input.getCardItemId(), input.getCardPwd(), input.getCardTypeId(), item.getSupportAmount(), cardType.getSaleRatio());

        //获得卡对象id
        Integer cardId = _cardRepository.insertAndGetId(card);

        //初始化订单
        Order order = Order.generateOrder(input.getCardNum(), cardId, input.getCardTypeId(), input.getCardItemId(), input.getUserId());

        //调用第三方售卡接口
        ApiResult result = SaleCardApi.sale1Card(
                cardType.getCardCode(),
                item.getSupportAmount().toString(),
                input.getCardNum(),
                input.getCardPwd(),
                order.getOrderNum());

        //根据寄售结果处理订单
        dealResult(order, result.getMessage(), result.getResultCode(), result.getOrderNo());

        _orderRepository.insert(order);
        return result;
    }
}
