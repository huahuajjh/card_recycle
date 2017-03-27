package com.tqmars.cardrecycle.application.admin.business.dto;

import com.excel.util.annotation.OutputColAnnotation;

/**
 * Created by jjh on 17-3-26.
 */
public class QueryMerchantAsListOutput {
    @OutputColAnnotation(colCoord = 0)
    private String account;

    @OutputColAnnotation(colCoord = 1)
    private String tel;

    @OutputColAnnotation(colCoord = 2)
    private String qq;

    @OutputColAnnotation(colCoord = 3)
    private String idCardNum;

    @OutputColAnnotation(colCoord = 4)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    @Override
    public String toString() {
        return "QueryMerchantAsListOutput{" +
                "account='" + account + '\'' +
                ", tel='" + tel + '\'' +
                ", qq='" + qq + '\'' +
                ", name='" + name + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                '}';
    }
}
