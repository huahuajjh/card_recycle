package com.tqmars.cardrecycle.application.bank.dto;

/**
 * Created by jjh on 1/22/17.
 */
public class QueryBankAccountOutput {
    private Integer id = 0;
    private String cardNum;
    private String name;
    private Integer userId = 0;
    private String bankName;
    private Integer bankId = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "QueryBankAccountOutput{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", bankName='" + bankName + '\'' +
                ", bankId=" + bankId +
                '}';
    }
}
