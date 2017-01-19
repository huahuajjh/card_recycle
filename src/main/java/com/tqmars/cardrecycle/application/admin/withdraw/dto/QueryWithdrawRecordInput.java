package com.tqmars.cardrecycle.application.admin.withdraw.dto;

/**
 * Created by jjh on 1/19/17.
 */
public class QueryWithdrawRecordInput {
    private int status;
    private String account;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
