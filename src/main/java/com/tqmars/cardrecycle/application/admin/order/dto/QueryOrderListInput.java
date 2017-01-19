package com.tqmars.cardrecycle.application.admin.order.dto;

import java.util.Date;

/**
 * Created by jjh on 1/18/17.
 */
public class QueryOrderListInput {
    private String orderNum;
    private String cardNum;
    private int orderStatus;
    private Date createFrom;
    private Date createTo;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateFrom() {
        return createFrom;
    }

    public void setCreateFrom(Date createFrom) {
        this.createFrom = createFrom;
    }

    public Date getCreateTo() {
        return createTo;
    }

    public void setCreateTo(Date createTo) {
        this.createTo = createTo;
    }
}
