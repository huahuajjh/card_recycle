package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.RechargeableCardType;
import com.tqmars.cardrecycle.domain.repositories.ICardTypeRepository;

/**
 * Created by jjh on 1/17/17.
 */
public class CardTypeRepository extends RepositoryBase<RechargeableCardType,Integer> implements ICardTypeRepository {
    public CardTypeRepository(DbContext _context) {
        super(_context);
    }
}
