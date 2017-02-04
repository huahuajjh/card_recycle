package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.withdraw.IWithdrawAppService;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordInput;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordOutput;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;

/**
 * Created by jjh on 2/4/17.
 */
public class TestWithdrawRecordAppService {
    @Test
    public void testQueryWithdrawRecord(){
        IWithdrawAppService service = ServiceLocator.getInstance().getService("WithDrawAppService",IWithdrawAppService.class);
        QueryWithdrawRecordInput input = new QueryWithdrawRecordInput();
        input.setProcessStatus(0);
        System.out.println(Serialization.toObject(service.queryWithdrawRecord(input), QueryWithdrawRecordOutput.class).getBankName());
        System.out.println(service.queryWithdrawRecord(input));

    }

}
