package com.tqmars.cardrecycle.application.admin.card;

import com.tqmars.cardrecycle.application.admin.card.dto.*;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardType;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardTypeItem;
import com.tqmars.cardrecycle.domain.services.card.ICardTypeDomainService;

import java.util.List;

/**
 * Created by jjh on 1/17/17.
 */
public class AdminCardTypeAppService extends BaseAppService implements IAdminCardTypeAppService{
    private ICardTypeDomainService _cardTypeDomainService;

    public AdminCardTypeAppService(ICardTypeDomainService _cardTypeDomainService) {
        this._cardTypeDomainService = _cardTypeDomainService;
    }

    @Override
    public void addCardType(AddCardTypeInput input) {
        System.out.println(AutoMapper.mapping(RechargeableCardType.class, input).toString());
        _cardTypeDomainService.addCardType(AutoMapper.mapping(RechargeableCardType.class, input));
    }

    @Override
    public void modifyCardType(ModifyCardTypeInput input) {
        _cardTypeDomainService.modifyCardType(AutoMapper.mapping(RechargeableCardType.class, input));
    }

    @Override
    public void delCardType(int id) {
        _cardTypeDomainService.delCardType(id);
    }

    @Override
    public List<QueryCardTypeOutput> queryCardType() {
        List<RechargeableCardType> list = _cardTypeDomainService.query();
        return AutoMapper.mapping(QueryCardTypeOutput.class, list);
    }

    @Override
    public void modifyCardTypeItem(ModifyCardTypeItemInput input) {
        _cardTypeDomainService.modifyCardTypeItem(AutoMapper.mapping(RechargeableCardTypeItem.class, input));
    }

    @Override
    public void delCardTypeItem(Integer id) {
        _cardTypeDomainService.delCardTypeItem(id);
    }

    @Override
    public List<QueryCardTypeItemOutput> queryCardTypeItem(Integer cardTypeId) {
        List<RechargeableCardTypeItem> list = _cardTypeDomainService.queryCardTypeItem(cardTypeId);
        return AutoMapper.mapping(QueryCardTypeItemOutput.class, list);
    }

    @Override
    public void addCardTypeItem(AddCardTypeItemInput input) {
        _cardTypeDomainService.addCardTypeItem(AutoMapper.mapping(RechargeableCardTypeItem.class, input));
    }
}
