package com.tqmars.cardrecycle.application.withdraw.dto;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/22/17.
 */
public class ApplyWithdrawInput {
    private BigDecimal withdrawAmount;
    private String withdrawPwd;
    private String bankName;
    private Integer userId;
    private Integer bankId;
    private String cardNum;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getWithdrawPwd() {
        return withdrawPwd;
    }

    public void setWithdrawPwd(String withdrawPwd) {
        this.withdrawPwd = withdrawPwd;
    }
}
