package com.tqmars.cardrecycle.application.admin.user.dto;

/**
 * Created by jjh on 1/17/17.
 */
public class ModifyUserInput {
    private Integer id;
    private String pwd;
    private String account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
