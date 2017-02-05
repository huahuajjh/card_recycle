package com.tqmars.cardrecycle.application.withdraw.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jjh on 1/22/17.
 */
public class QueryWithdrawRecordOutput {
    private Integer id;
    private BigDecimal withdrawAmount;
    private int processStatus;
    private Date processTime;
    private String cardNum;
    private Date applyTime;
    private BigDecimal serviceCharge;
    private BigDecimal actualAccountAmount;
    private String bankName;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getActualAccountAmount() {
        return actualAccountAmount;
    }

    public void setActualAccountAmount(BigDecimal actualAccountAmount) {
        this.actualAccountAmount = actualAccountAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "QueryWithdrawRecordOutput{" +
                "id=" + id +
                ", withdrawAmount=" + withdrawAmount +
                ", processStatus=" + processStatus +
                ", processTime=" + processTime +
                ", cardNum='" + cardNum + '\'' +
                ", applyTime=" + applyTime +
                ", serviceCharge=" + serviceCharge +
                ", actualAccountAmount=" + actualAccountAmount +
                ", bankName='" + bankName + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
