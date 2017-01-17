package com.tqmars.cardrecycle.application.net;

import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Random;

/**
 * Created by jjh on 1/14/17.
 */
public class Sms {

    public static String sendMsg(String to) throws IOException {
        String smsCode = get6RandomCode();
        String softVersion = "2014-06-30";
        String accountSid = "f93074d2b277ff7f2f4aae89251c5f99";
        String authToken = "9e149af04ea0cd058acc3bb03dc8745d";
        String timestamp = DateTool.getInstance().getNowTime();
        String sig = Md5.md5(accountSid+authToken+ timestamp).toUpperCase();
        String authorization = Md5.toBase64(accountSid+":"+timestamp);
        String appId = "fd0f27700c0e44c3b5778d020c133b3c";
        String param = "xxx科技,"+smsCode;
        String templateId = "35985";

        String api = "https://api.ucpaas.com/"+softVersion+"/Accounts/"+accountSid+"/Messages/templateSMS?sig="+sig;


        String json =  "{\"templateSMS\":{\"appId\":\"" + appId + "\",\"param\":\"" + param + "\",\"templateId\":\"" + templateId + "\",\"to\":\"" + to + "\"}}";
        HttpPost post = new HttpPost(api);
        post.addHeader("Content-Type", "application/json;charset=utf-8");
        post.addHeader("Accept", "application/json");
        post.addHeader("Authorization", authorization);
        StringEntity se = new StringEntity(json, "UTF-8");
        post.setEntity(se);

        HttpResponse response = null;
        try {
            response = new DefaultHttpClient().execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        return smsCode;
    }

    private static String get6RandomCode(){
        int[] codes = {0,1,2,3,4,5,6,7,8,9};
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += codes[new Random().nextInt(10)];
        }
        return code;
    }

}
