package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.domain.entities.data.WithdrawRecordDetails;
import com.tqmars.cardrecycle.domain.repositories.IWithdrawRecordDetailsRepository;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

/**
 * Created by jjh on 2/5/17.
 */
public class TestWithdrawRecordDetailsRepository {
    @Test
    public void testQuery(){
        IWithdrawRecordDetailsRepository repository = ServiceLocator.getInstance().getService("WithdrawRecordDetailsRepository",IWithdrawRecordDetailsRepository.class);
        repository.setEntityClass(WithdrawRecordDetails.class);
        repository.getAll().forEach(a->System.out.println(a.toString()));

    }
}
