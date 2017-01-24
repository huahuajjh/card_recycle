package com.tqmars.cardrecycle.application.withdraw.dto;

import java.util.Date;

/**
 * Created by jjh on 1/22/17.
 */
public class QueryWithdrawRecordInput {
    private Date from;
    private Date to;
    private int processStatus;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }
}
