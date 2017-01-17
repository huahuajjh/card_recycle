package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardTypeItem;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeItemRepository;

/**
 * Created by jjh on 1/17/17.
 */
public class CardTypeItemRepository extends RepositoryBase<RechargeableCardTypeItem,Integer> implements ICardTypeItemRepository {
    public CardTypeItemRepository(DbContext _context) {
        super(_context);
    }
}
