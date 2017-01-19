package com.tqmars.cardrecycle.application.admin.business.dto;

import java.util.Date;

/**
 * Created by jjh on 1/17/17.
 */
public class QueryBusinessListInput {
    private String account;
    private Date from;
    private Date to;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
}
