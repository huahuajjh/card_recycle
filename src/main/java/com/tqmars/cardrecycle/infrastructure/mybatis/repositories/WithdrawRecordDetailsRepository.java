package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecordDetails;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordDetailsRepository;

/**
 * Created by jjh on 2/5/17.
 */
public class WithdrawRecordDetailsRepository extends RepositoryBase<WithdrawRecordDetails,Integer> implements IWithdrawRecordDetailsRepository{
    public WithdrawRecordDetailsRepository(DbContext _context) {
        super(_context);
    }
}
