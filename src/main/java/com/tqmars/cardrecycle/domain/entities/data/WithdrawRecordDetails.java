package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jjh on 1/19/17.
 */
@Table(name = "v_withdraw_record")
public class WithdrawRecordDetails extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id;

    @Column(name = "business_id")
    private String businessId;

    @Column(name = "name")
    private String name;

    @Column(name = "withdraw_amount")
    private BigDecimal withdrawAmount;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "process_status")
    private int processStatus;

    @Column(name = "process_time")
    private Date processTime;

    @Column(name = "card_number")
    private String cardNum;

    @Column(name = "account")
    private String account;

    @Column(name = "msg")
    private String msg;

    @Column(name = "apply_time")
    private Date applyTime;

    @Column(name = "service_charge")
    private BigDecimal serviceCharge;

    @Column(name = "actual_account_amount")
    private BigDecimal actualAmount;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

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

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "WithdrawRecordDetails{" +
                "id=" + id +
                ", businessId='" + businessId + '\'' +
                ", name='" + name + '\'' +
                ", withdrawAmount=" + withdrawAmount +
                ", balance=" + balance +
                ", processStatus=" + processStatus +
                ", processTime=" + processTime +
                ", cardNum='" + cardNum + '\'' +
                ", account='" + account + '\'' +
                ", msg='" + msg + '\'' +
                ", applyTime=" + applyTime +
                ", serviceCharge=" + serviceCharge +
                ", actualAmount=" + actualAmount +
                '}';
    }
}
