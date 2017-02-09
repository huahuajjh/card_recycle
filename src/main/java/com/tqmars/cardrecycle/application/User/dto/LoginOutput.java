package com.tqmars.cardrecycle.application.User.dto;

import java.sql.Timestamp;

/**
 * Created by jjh on 2/4/17.
 */
public class LoginOutput {
    private Integer id;
    private String token;
    private String name;
    private String account;
    private String tel;
    private String qq;
    private String businessId;
    private Timestamp lastLoginTime;

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "LoginOutput{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", tel='" + tel + '\'' +
                ", qq='" + qq + '\'' +
                ", businessId='" + businessId + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
