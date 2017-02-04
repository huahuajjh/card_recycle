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
        int cardTypeId = _cardTypeRepository.insertAndGetId(cardType);
        _cardTypeRepository.commit();
    }

    @Override
    public void modifyCardType(RechargeableCardType cardType) {
        _cardTypeRepository.update(cardType);
        _cardTypeRepository.commit();
    }

    @Override
    public void delCardType(Integer cardTypeId) {
        _cardTypeRepository.deleteById(cardTypeId);
        _cardTypeItemRepository.deleteWithCondition("tb_rechargeable_card_type_id="+cardTypeId);

        _cardTypeItemRepository.commit();
        _cardTypeRepository.commit();
    }

    @Override
    public List<RechargeableCardType> query() {
        List<RechargeableCardType> list = _cardTypeRepository.getAll();
        _cardTypeRepository.commit();
        return list;
    }

    @Override
    public void modifyCardTypeItem(RechargeableCardTypeItem item) {
        RechargeableCardTypeItem i = _cardTypeItemRepository.get(item.getId());
        if(null == i){
            _cardTypeItemRepository.commit();
            return;
        }

        i.setSupportAmount(item.getSupportAmount());
        _cardTypeItemRepository.update(i);
        _cardTypeItemRepository.commit();
    }

    @Override
    public void delCardTypeItem(Integer id) {
        _cardTypeItemRepository.deleteById(id);
        _cardTypeItemRepository.commit();
    }

    @Override
    public List<RechargeableCardTypeItem> queryCardTypeItem(Integer cardTypeId) {
        List<RechargeableCardTypeItem> list = _cardTypeItemRepository.getAllWithCondition("tb_rechargeable_card_type_id="+cardTypeId);
        _cardTypeItemRepository.commit();
        return list;
    }

    @Override
    public void addCardTypeItem(RechargeableCardTypeItem item) {
        _cardTypeItemRepository.insert(item);
        _cardTypeItemRepository.commit();
    }
}
