package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.Wallet;
import com.tqmars.cardrecycle.domain.repositories.IWalletRepository;

/**
 * Created by jjh on 1/19/17.
 */
public class WalletRepository extends RepositoryBase<Wallet,Integer> implements IWalletRepository {
    public WalletRepository(DbContext _context) {
        super(_context);
    }
}
