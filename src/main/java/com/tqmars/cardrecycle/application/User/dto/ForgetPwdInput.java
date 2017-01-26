package com.tqmars.cardrecycle.application.User.dto;

/**
 * Created by jjh on 1/26/17.
 */
public class ForgetPwdInput {
    private String newPwd;
    private String account;
    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
