package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecord;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordRepository;

/**
 * Created by jjh on 1/19/17.
 */
public class WithdrawRecordRepository extends RepositoryBase<WithdrawRecord,Integer> implements IWithdrawRecordRepository {
    public WithdrawRecordRepository(DbContext _context) {
        super(_context);
    }
}
