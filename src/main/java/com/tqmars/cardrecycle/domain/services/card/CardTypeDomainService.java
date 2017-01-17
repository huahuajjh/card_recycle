package com.tqmars.cardrecycle.domain.services.card;

import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardType;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardTypeItem;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeItemRepository;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeRepository;

import java.util.List;

/**
 * Created by jjh on 1/17/17.
 */
public class CardTypeDomainService implements ICardTypeDomainService{
    ICardTypeRepository _cardTypeRepository;
    ICardTypeItemRepository _cardTypeItemRepository;

    public CardTypeDomainService(ICardTypeRepository _cardTypeRepository, ICardTypeItemRepository _cardTypeItemRepository) {
        this._cardTypeRepository = _cardTypeRepository;
        this._cardTypeItemRepository = _cardTypeItemRepository;

        this._cardTypeRepository.setEntityClass(RechargeableCardType.class);
        this._cardTypeItemRepository.setEntityClass(RechargeableCardTypeItem.class);
    }

    @Override
    public void addCardType(RechargeableCardType cardType) {
        _cardTypeRepository.insert(cardType);

        _cardTypeRepository.commit();
    }

    @Override
    public void modifyCardType(RechargeableCardType cardType) {

    }

    @Override
    public void delCardType(RechargeableCardType cardType) {

    }

    @Override
    public List<RechargeableCardType> query(String where) {
        return null;
    }

    @Override
    public void modifyCardTypeItem(RechargeableCardTypeItem item) {

    }

    @Override
    public void delCardTypeItem(Integer id) {

    }

    @Override
    public List<RechargeableCardTypeItem> queryCardTypeItem(Integer cardTypeId) {
        return null;
    }
}
