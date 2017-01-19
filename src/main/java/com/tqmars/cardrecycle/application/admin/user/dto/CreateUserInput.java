package com.tqmars.cardrecycle.application.admin.user.dto;

/**
 * Created by jjh on 1/17/17.
 */
public class CreateUserInput {
    private String pwd;
    private String account;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
