package com.tqmars.cardrecycle.application.withdraw.dto;


import java.sql.Date;

/**
 * Created by jjh on 1/22/17.
 */
public class QueryWithdrawRecordInput {
    private Date from;
    private Date to;
    private Integer processStatus;
    private int index;
    private int count;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }
}
