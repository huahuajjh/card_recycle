package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_withdraw_record")
public class WithdrawRecord extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "withdraw_time")
    private Date withdrawTime;

    @Column(name = "back_name")
    private String backName;

    @Column(name = "tb_back_id")
    private Integer backId;

    @Column(name = "withdraw_amount")
    private BigDecimal withdrawAmount;

    @Column(name = "service_charge")
    private BigDecimal serviceCharge;

    @Column(name = "actual_account_amount")
    private BigDecimal actualAccountAmount;

    @Column(name = "process_time")
    private Date processTime;

    @Column(name = "apply_time")
    private Date applyTime;

    @Column(name = "process_status")
    private int processStatus;

    @Column(name = "tb_user_id")
    private Integer userId;

    @Column(name = "msg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(Date withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public Integer getBackId() {
        return backId;
    }

    public void setBackId(Integer backId) {
        this.backId = backId;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
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

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WithdrawRecord{" +
                "id=" + id +
                ", withdrawTime=" + withdrawTime +
                ", backName='" + backName + '\'' +
                ", backId=" + backId +
                ", withdrawAmount=" + withdrawAmount +
                ", serviceCharge=" + serviceCharge +
                ", actualAccountAmount=" + actualAccountAmount +
                ", processTime=" + processTime +
                ", applyTime=" + applyTime +
                ", processStatus=" + processStatus +
                ", userId=" + userId +
                ", msg='" + msg + '\'' +
                '}';
    }

    public void withdraw(){
        this.processStatus = 1;
    }
}
