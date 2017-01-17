package com.tqmars.cardrecycle.domain.services.card;

import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardType;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardTypeItem;

import java.util.List;

/**
 * Created by jjh on 1/17/17.
 */
public interface ICardTypeDomainService {
    void addCardType(RechargeableCardType cardType);
    void modifyCardType(RechargeableCardType cardType);
    void delCardType(RechargeableCardType cardType);
    List<RechargeableCardType> query(String where);
    void modifyCardTypeItem(RechargeableCardTypeItem item);
    void delCardTypeItem(Integer id);
    List<RechargeableCardTypeItem> queryCardTypeItem(Integer cardTypeId);

}
