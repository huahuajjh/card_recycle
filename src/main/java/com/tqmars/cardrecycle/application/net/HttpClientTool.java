package com.tqmars.cardrecycle.application.net;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by jjh on 1/14/17.
 */
public class HttpClientTool {
    public static String post(String url, Map<String,String> parameters){
        StringBuilder sb = new StringBuilder();
        if(null != parameters && parameters.size() > 0){
            parameters.entrySet().forEach(p->{
                if(sb.length() != 0){
                    sb.append("&").append(p.getKey()).append(p.getValue());
                }
                sb.append(p.getKey()).append(p.getValue());
            }
            );
        }
        HttpPost post = new HttpPost(url+"?"+sb.toString());
        try {
            HttpResponse response = new DefaultHttpClient().execute(post);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            LoggerFactory.getLogger().error(e.getMessage());
            return null;
        }

    }

    public static String get(String url, Map<String,String> parameters){
        StringBuilder sb = new StringBuilder();
        if(null != parameters && parameters.size() > 0){
            parameters.entrySet().forEach(p->{
                        if(sb.length() == 0){
                            sb.append(p.getKey()).append("=").append(p.getValue());
                        }else {
                            sb.append("&").append(p.getKey()).append("=").append(p.getValue());
                        }
                    }
            );
        }

        HttpGet get = new HttpGet(url+"?"+sb.toString());
        System.out.println(sb.toString());
        try {
            HttpResponse response = new DefaultHttpClient().execute(get);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
            LoggerFactory.getLogger().error(e.getMessage());
            return null;
        }

    }
}
