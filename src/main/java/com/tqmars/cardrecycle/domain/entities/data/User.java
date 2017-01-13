package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

/**
 * Created by jjh on 1/13/17.
 */
@Table(name = "tb_user")
public class User {
    @Column(name = "id")
    private int id;

    @Column(name = "account")
    private String account;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "tel")
    private String tel;

    @Column(name = "QQ")
    private String qq;

    @Column(name = "business_id")
    private String businessId;

    @Column(name = "business_pwd")
    private String getBusinessPwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGetBusinessPwd() {
        return getBusinessPwd;
    }

    public void setGetBusinessPwd(String getBusinessPwd) {
        this.getBusinessPwd = getBusinessPwd;
    }
}
