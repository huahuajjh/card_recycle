package com.tqmars.cardrecycle.application.security.dto;

/**
 * Created by jjh on 1/16/17.
 */
public class ChangeWithdrawPwdInput {
    private String newPwd;
    private String oldPwd;
    private String token;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

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
}
