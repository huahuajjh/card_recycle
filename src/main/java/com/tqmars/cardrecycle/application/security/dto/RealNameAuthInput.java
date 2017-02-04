package com.tqmars.cardrecycle.application.security.dto;

/**
 * Created by jjh on 1/16/17.
 */
public class RealNameAuthInput {
    private String idNum;
    private String name;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
