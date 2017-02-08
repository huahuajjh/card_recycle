package com.tqmars.cardrecycle.application.User.dto;

/**
 * Created by jjh on 2/9/17.
 */
public class ChangeTelInput {
    private Integer userId;
    private String newTel;
    private String sms;

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNewTel() {
        return newTel;
    }

    public void setNewTel(String newTel) {
        this.newTel = newTel;
    }
}
