package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Bank;
import com.tqmars.cardrecycle.domain.repositories.IBankRepository;

/**
 * Created by jjh on 1/16/17.
 */
public class BankRepository extends RepositoryBase<Bank,Integer> implements IBankRepository{
    public BankRepository(DbContext _context) {
        super(_context);
    }
}
