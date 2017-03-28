package com.tqmars.cardrecycle.infrastructure.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by jjh on 3/28/17.
 */
public class Json {
    private static Json INSTANCE = new Json();
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson;

    private Json(){}

    static {
        builder.setDateFormat("yyyy-MM-dd");
        gson = builder.create();
    }

    public static Json getInstance(){
        return INSTANCE;
    }

    public static Json setDateFormat(String dataFormat){
        builder.setDateFormat(dataFormat);
        gson = builder.create();
        return INSTANCE;
    }

    public String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public <T> T toObject(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    public <T> List<T> toList(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    public <T> List<T> toList(String jsonString) {
        Type t = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(jsonString, t);
    }

    public String toJsonWithFormatter(Object data,String msg,int code){
        return toJson(Formatter.getInstance(data,msg,code));
    }

    public String toJsonWithPageFormatter(Object data,String msg,int code,int count){
        return toJson(PageFormatter.getInstance(data,msg,code,count));
    }

}
