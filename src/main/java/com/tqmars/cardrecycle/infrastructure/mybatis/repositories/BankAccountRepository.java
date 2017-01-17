package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.BankAccount;
import com.tqmars.cardrecycle.domain.repositories.IBankAccountRepository;

/**
 * Created by jjh on 1/16/17.
 */
public class BankAccountRepository extends RepositoryBase<BankAccount,Integer> implements IBankAccountRepository{
    public BankAccountRepository(DbContext _context) {
        super(_context);
    }
}
