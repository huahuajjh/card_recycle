package com.tqmars.cardrecycle.application.card;

import com.tqmars.cardrecycle.application.admin.card.dto.QueryCardTypeOutput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.base.BaseAppService;
import com.tqmars.cardrecycle.application.card.dto.QueryCardItemOutput;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardType;
import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardTypeItem;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeItemRepository;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeRepository;

import java.util.List;

/**
 * Created by jjh on 1/21/17.
 */
public class CardTypeAppService extends BaseAppService implements ICardTypeAppService {
    private ICardTypeRepository _cardTypeRepository;
    private ICardTypeItemRepository _cardItemRepository;

    public CardTypeAppService(ICardTypeRepository _cardTypeRepository, ICardTypeItemRepository _cardItemRepository) {
        this._cardTypeRepository = _cardTypeRepository;
        this._cardItemRepository = _cardItemRepository;

        this._cardTypeRepository.setEntityClass(RechargeableCardType.class);
        this._cardItemRepository.setEntityClass(RechargeableCardTypeItem.class);
    }

    @Override
    public List<QueryCardTypeOutput> queryCardTypeList() {
        List<RechargeableCardType> list = _cardTypeRepository.getAll();
        return AutoMapper.mapping(QueryCardTypeOutput.class, list);
    }

    @Override
    public List<QueryCardItemOutput> queryCardItem(Integer cardTypeId) {
        List<RechargeableCardTypeItem> list = _cardItemRepository.getAllWithCondition("tb_rechargeable_card_type_id="+cardTypeId);
        return AutoMapper.mapping(QueryCardItemOutput.class, list);
    }
}
