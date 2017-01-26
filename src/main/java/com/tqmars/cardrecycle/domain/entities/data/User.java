package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;

/**
 * Created by jjh on 1/13/17.
 */
@Table(name = "tb_user")
public class User extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "account")
    private String account;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "tel")
    private String tel;

    @Column(name = "qq")
    private String qq;

    @Column(name = "business_id")
    private String businessId;

    @Column(name = "business_pwd")
    private String businessPwd;

    @Column(name = "token")
    private String token;

    @Column(name = "name")
    private String name;

    @Column(name = "withdraw_pwd")
    private String withdrawPwd;

    @Column(name = "id_card_num")
    private String idCardNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWithdrawPwd() {
        return withdrawPwd;
    }

    public void setWithdrawPwd(String withdrawPwd) {
        this.withdrawPwd = withdrawPwd;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getBusinessPwd() {
        return businessPwd;
    }

    public void setBusinessPwd(String getBusinessPwd) {
        this.businessPwd = getBusinessPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tel='" + tel + '\'' +
                ", qq='" + qq + '\'' +
                ", businessId='" + businessId + '\'' +
                ", businessPwd='" + businessPwd + '\'' +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", withdrawPwd='" + withdrawPwd + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                '}';
    }

    public void changPwd(String newPwd) {
        this.pwd = Md5.md5WithSalt(newPwd);
    }
    public void changWithdrawPwd(String pwd){
        this.withdrawPwd = pwd;
    }
    public void realNameAuth(String name,String idCardNum){
        this.name = name;
        this.idCardNum = idCardNum;
    }

}
