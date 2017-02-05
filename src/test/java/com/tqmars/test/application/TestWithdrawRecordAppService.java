package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.admin.withdraw.IAdminWithdrawAppService;
import com.tqmars.cardrecycle.application.withdraw.IWithdrawAppService;
import com.tqmars.cardrecycle.application.withdraw.dto.QueryWithdrawRecordInput;
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
        input.setIndex(1);
        input.setCount(1);
//        System.out.println(Serialization.toObject(service.queryWithdrawRecord(input), Formatter.class).getCode());
        System.out.println(service.queryWithdrawRecord(input));

    }

    @Test
    public void testAdminQueryWithdrawRecord(){
        IAdminWithdrawAppService service = ServiceLocator.getInstance().getService("AdminWithdrawAppService",IAdminWithdrawAppService.class);
        com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordInput input = new com.tqmars.cardrecycle.application.admin.withdraw.dto.QueryWithdrawRecordInput();
        input.setIndex(1);
        input.setCount(5);
        input.setAccount("measca@qq.com");
        input.setStatus(3);
        System.out.println(service.queryWithdrawRecord(input));
    }

}
