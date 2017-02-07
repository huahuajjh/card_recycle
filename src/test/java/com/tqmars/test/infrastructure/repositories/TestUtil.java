package com.tqmars.test.infrastructure.repositories;

import com.tqmars.cardrecycle.application.admin.order.IAdminOrderAppService;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListInput;
import com.tqmars.cardrecycle.domain.entities.data.Admin;
import com.tqmars.cardrecycle.domain.entities.data.OrderDetails;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjh on 1/13/17.
 */
public class TestUtil {
    @Test
    public void testToEntity(){
        System.out.println(Md5.md5WithSalt("abcd123"));
    }

    @Test
    public void testToEntityList() throws InstantiationException, IllegalAccessException {

    }

    @Test
    public void testMd5() throws InterruptedException {
//        System.out.println(DigestUtils.md5Hex("123"));
//        System.out.println(Md5.md5("123"));
//        System.out.println(System.currentTimeMillis());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        System.out.println(OrderNumGenerator.generateOrderNum());
//        HashMap<String,Object> hs = new HashMap<>();
//        for (int i = 0; i < 10000000; i++) {
//            hs.put(OrderNumGenerator.generateOrderNum(),i);
//        }
//        System.out.println(hs.size());

//        System.out.println(Serialization.toJsonWithFormatter("huahuajjh","success",600));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }

        list.forEach(
                (i)-> {
                    if(i>10){
                        System.out.println(i);
                    }
                }
        );

    }

    @Test
    public void testOrderNumGenerator(){
//        System.out.println(OrderNumGenerator.generateOrderNum().replace("-",""));
        System.out.println(OrderDetails.OrderStatus.FAIL);
    }

    @Test
    public void testDate(){
//        System.out.println(DateTool.getInstance().getNowTime("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(Date.valueOf("2014-01-01"));
//        System.out.println(Serialization.toObject("{\"index\":1,\"count\":15,\"from\":\"1990-01-01\",\"to\":\"2990-01-01\"}", QueryWithdrawRecordInput.class).getFrom());
//        System.out.println(Serialization.toObject("{\"index\":1,\"count\":15}", QueryWithdrawRecordInput.class).getCount());


//        System.out.println(new BigDecimal("50").compareTo(new BigDecimal("500")));
//        String s = "[{\"name\":\"zs\",\"age\":\"12\"},{\"name\":\"zs\",\"age\":\"11\"},{\"name\":\"zs\",\"age\":\"13\"}]";
//        System.out.println(((P)(Serialization.toList(s).get(2))).getAge());
//        Type t = new TypeToken<List<P>>(){}.getType();
//        System.out.println(((P)Serialization.toList(s,t).get(0)).getName());
        System.out.println(DateTool.getInstance().getNowSqlTime());

    }

    @Test
    public void test(){
        s();
        s1();
    }

    @Async
    public void s(){
        System.out.println("task1 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task1 done");
    }

    @Async
    public void s1(){
        System.out.println("task2 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task2 done");
    }

    @Test
    public void testSingleton(){
//        IRepository<User,Integer> r1 = (IRepository<User,Integer>)ServiceLocator.getInstance().getService("RepositoryBase",IRepository.class);
//
//        r1.setEntityClass(User.class);
//        System.out.println(r1.countWithCondition("id>0 limit 1,2"));

//        IRepository<Admin,Integer> r2 = (IRepository<Admin,Integer>)ServiceLocator.getInstance().getService("RepositoryBase",IRepository.class);
//        r2.setEntityClass(Admin.class);

//        System.out.println(r1.hashCode());
//        System.out.println(r2.hashCode());
//        System.out.println(r1.get(1).getName());

//        IAdminOrderAppService service = ServiceLocator.getInstance().getService("AdminOrderAppService",IAdminOrderAppService.class);
//        QueryOrderListInput input = new QueryOrderListInput();
//        input.setIndex(1);
//        input.setCount(15);
//        System.out.println(service.queryOrderList(input));


    }

}
