package com.tqmars.cardrecycle.application.User.dto;

/**
 * Created by jjh on 1/14/17.
 */
public class LoginInput {
    private String account;
    private String pwd;
    private String vCode;

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

    public String getVcode() {
        return vCode;
    }

    public void setVcode(String vcode) {
        this.vCode = vcode;
    }

}
