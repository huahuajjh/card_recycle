package com.tqmars.cardrecycle.application.User.dto;

/**
 * Created by jjh on 1/14/17.
 */
public class CreateUserInput {
    private String account;
    private String pwd;
    private String qq;
    private String tel;
    private String businessId;
    private String businessPwd;
    private String smsCode;
    private String withdrawPwd;

    public String getWithdrawPwd() {
        return withdrawPwd;
    }

    public void setWithdrawPwd(String withdrawPwd) {
        this.withdrawPwd = withdrawPwd;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessPwd() {
        return businessPwd;
    }

    public void setBusinessPwd(String businessPwd) {
        this.businessPwd = businessPwd;
    }

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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "CreateUserInput{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", qq='" + qq + '\'' +
                ", tel='" + tel + '\'' +
                ", businessId='" + businessId + '\'' +
                ", businessPwd='" + businessPwd + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", withdrawPwd='" + withdrawPwd + '\'' +
                '}';
    }
}
