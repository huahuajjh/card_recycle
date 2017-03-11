package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_withdraw_record")
public class WithdrawRecord extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "withdraw_time")
    private Timestamp withdrawTime;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "tb_bank_id")
    private Integer bankId;

    @Column(name = "withdraw_amount")
    private BigDecimal withdrawAmount;

    @Column(name = "service_charge")
    private BigDecimal serviceCharge;

    @Column(name = "actual_account_amount")
    private BigDecimal actualAccountAmount;

    @Column(name = "process_time")
    private Timestamp processTime;

    @Column(name = "apply_time")
    private Timestamp applyTime;

    @Column(name = "process_status")
    private int processStatus;

    @Column(name = "tb_user_id")
    private Integer userId;

    @Column(name = "msg")
    private String msg;

    @Column(name = "process_msg")
    private String processMsg;

    @Column(name = "bank_card_num")
    private String cardNum;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getProcessMsg() {
        return processMsg;
    }

    public void setProcessMsg(String processMsg) {
        this.processMsg = processMsg;
    }

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

    public Timestamp getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(Timestamp withdrawTime) {
        this.withdrawTime = withdrawTime;
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

    public Timestamp getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Timestamp processTime) {
        this.processTime = processTime;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
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
                ", bankName='" + bankName + '\'' +
                ", bankId=" + bankId +
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

    public void userWithdraw(String bankName,Integer userId,Integer bankId,BigDecimal amount,String bankAccount){

        this.setCardNum(bankAccount);
        this.setWithdrawTime(DateTool.getInstance().getNowSqlTime());
        this.setWithdrawAmount(amount);
        this.setBankName(bankName);
        this.setUserId(userId);
        this.setBankId(bankId);
        this.setProcessStatus(0);
        this.setMsg("处理中");
        this.setProcessTime(DateTool.getInstance().getNowSqlTime());
        this.setApplyTime(DateTool.getInstance().getNowSqlTime());

        if(amount.compareTo(new BigDecimal("500")) < 0){
            this.setServiceCharge(new BigDecimal("1.00"));
            this.setActualAccountAmount(this.getWithdrawAmount());
        }else {
            this.setServiceCharge(new BigDecimal("0.00"));
            this.setActualAccountAmount(this.getWithdrawAmount());
        }

    }
}
