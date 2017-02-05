package com.tqmars.cardrecycle.application.admin.withdraw.dto;

/**
 * Created by jjh on 2/5/17.
 */
public class DealwithWithdrawApplyInput {
    private Integer status;
    private String processMsg;
    private Integer withdrawRecordId;

    public Integer getWithdrawRecordId() {
        return withdrawRecordId;
    }

    public void setWithdrawRecordId(Integer withdrawRecordId) {
        this.withdrawRecordId = withdrawRecordId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProcessMsg() {
        return processMsg;
    }

    public void setProcessMsg(String processMsg) {
        this.processMsg = processMsg;
    }
}
