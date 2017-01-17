package com.tqmars.cardrecycle.application.admin.user.dto;

/**
 * Created by jjh on 1/16/17.
 */
public class LoginInput {
    private String account;
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
