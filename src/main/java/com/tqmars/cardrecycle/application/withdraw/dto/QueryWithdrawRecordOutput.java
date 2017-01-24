package com.tqmars.cardrecycle.application.withdraw.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jjh on 1/22/17.
 */
public class QueryWithdrawRecordOutput {
    private Integer id;
    private String name;
    private BigDecimal withdrawAmount;
    private int processStatus;
    private Date processTime;
    private String cardNum;
    private Date applyTime;
    private BigDecimal serviceCharge;
    private BigDecimal actualAmoung;
    private String bankName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getActualAmoung() {
        return actualAmoung;
    }

    public void setActualAmoung(BigDecimal actualAmoung) {
        this.actualAmoung = actualAmoung;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
