package com.tqmars.cardrecycle.application.security.dto;

/**
 * Created by jjh on 1/16/17.
 */
public class ChangeWithdrawPwdInput {
    private String newPwd;
    private String token;
    private Integer id;

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
