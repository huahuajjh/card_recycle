package com.tqmars.cardrecycle.infrastructure.serialization;

/**
 * Created by jjh on 1/15/17.
 */
public class Formatter {
    private Object data;
    private String msg;
    private int code;

    Formatter(){}

    public static Formatter getInstance(){
        return new Formatter();
    }

    public static Formatter getInstance(Object _data, String _msg, int _code){
        Formatter formatter = new Formatter();
        formatter.setData(_data);
        formatter.setCode(_code);
        formatter.setMsg(_msg);
        return formatter;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
