package com.tqmars.cardrecycle.application.admin.user.dto;

/**
 * Created by jjh on 1/16/17.
 */
public class ChangePwdInput {
    private String newPwd;
    private String oldPwd;
    private String token;

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
