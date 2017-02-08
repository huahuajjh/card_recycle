package com.tqmars.cardrecycle.application.api.dto;

/**
 * Created by jjh on 2/7/17.
 */
public class SellCardOutput {
    private int status;
    private String msg;
    private String businessOrderNo;
    private String apiOrderNo;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBusinessOrderNo() {
        return businessOrderNo;
    }

    public void setBusinessOrderNo(String businessOrderNo) {
        this.businessOrderNo = businessOrderNo;
    }

    public String getApiOrderNo() {
        return apiOrderNo;
    }

    public void setApiOrderNo(String apiOrderNo) {
        this.apiOrderNo = apiOrderNo;
    }
}
