package com.tqmars.test.infrastructure.util;

import com.tqmars.cardrecycle.application.User.dto.CreateUserInput;
import com.tqmars.cardrecycle.application.automapper.AutoMapper;
import com.tqmars.cardrecycle.application.net.HttpClientTool;
import com.tqmars.cardrecycle.application.net.Sms;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.SaleCardApi;
import com.tqmars.cardrecycle.domain.entities.data.User;
import com.tqmars.cardrecycle.infrastructure.StringTools.Des;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jjh on 1/14/17.
 */
public class TestUtil {
    @Test
    public void testVCode() throws IOException {
//        VCodeGenerator.CodeObj vcode = VCodeGenerator.getVCode(100,50);
//        System.out.println(vcode.getvCode());


//        System.out.println(new Date());
//        System.out.println(DateTool.getInstance().getNowTime());
//        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//        System.out.println(s.format(new Date()));
//        System.out.println(Md5.toBase64("123"));
//        System.out.println(Md5.getFromBase64("MTIz"));
//        System.out.println(111);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Sms.sendMsg("15928981624");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
//        Sms.sendMsg("15928981624");
    }

    @Test
    public void testMapper(){
        List<CreateUserInput> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            CreateUserInput input = new CreateUserInput();
            input.setPwd("123"+i);
            input.setAccount("huahuajjh"+i);
            input.setQq("703825021"+i);
            input.setTel("18817676235"+i);
            list.add(input);
        }
        System.out.println(AutoMapper.mapping(User.class,list));

    }

    @Test
    public void testDecimal(){
        BigDecimal a = new BigDecimal(112);
        BigDecimal b = new BigDecimal(32);



        System.out.println(a.subtract(b));
    }

    @Test
    public void testPost(){
        Map<String,String> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","22");

        String r = HttpClientTool.get("https://www.baidu.com",map);
        System.out.println(r);
    }

    @Test
    public void testConfigTool(){
        System.out.println(PropertiesFileTool.readByKey("businessPwd"));
    }

    @Test
    public void testLog(){
        LoggerFactory.getLogger().error("test log");
    }

    @Test
    public void testSb(){
        StringBuilder sb = new StringBuilder();
        sb.append("aad");
        System.out.println(sb.length());
    }

    @Test
    public void testThirdApi(){
//        ApiResult r = SaleCardApi.sale1Card("SZX","12.00","123131231","12312313");
//        System.out.println(r.getResultCode());
    }

    @Test
    public void testDes() throws Exception {
//        System.out.println(Des.toDes3("123"));
//        System.out.println(Des.decode(Des.toDes3("123")));
//        System.out.println(URLEncoder.encode("I39XTT61/SU="));
//        System.out.println(new BigDecimal(12).toString());
//        System.out.println(BigDecimal.valueOf(12.88).multiply(BigDecimal.valueOf(0.5)));
    }
}
