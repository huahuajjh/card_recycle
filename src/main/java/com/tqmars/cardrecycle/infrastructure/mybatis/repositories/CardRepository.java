package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.RechargeableCard;
import com.tqmars.cardrecycle.domain.repositories.ICardRepository;

/**
 * Created by jjh on 1/23/17.
 */
public class CardRepository extends RepositoryBase<RechargeableCard,Integer> implements ICardRepository {
    public CardRepository(DbContext _context) {
        super(_context);
    }
}
