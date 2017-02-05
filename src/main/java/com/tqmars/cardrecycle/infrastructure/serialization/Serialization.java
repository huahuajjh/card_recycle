package com.tqmars.cardrecycle.infrastructure.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by jjh on 1/11/17.
 */
public final class Serialization {
    private static GsonBuilder builder = new GsonBuilder();

    private static Gson gson;
    static {
        builder.setDateFormat("yyyy-MM-dd");
        gson = builder.create();
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    public static <T> List<T> toList(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    public static String toJsonWithFormatter(Object data,String msg,int code){
        return toJson(Formatter.getInstance(data,msg,code));
    }

    public static String toJsonWithPageFormatter(Object data,String msg,int code,int count){
        return toJson(PageFormatter.getInstance(data,msg,code,count));
    }
}
