package com.tqmars.cardrecycle.application.admin.order.dto;

import com.excel.util.annotation.OutputColAnnotation;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by jjh on 17-3-25.
 */
public class QueryOrderListAsListOutput {
    @OutputColAnnotation(colCoord = 0)
    private Timestamp orderTime;

    @OutputColAnnotation(colCoord = 1)
    private String orderNum;

    @OutputColAnnotation(colCoord = 2)
    private BigDecimal actualAmount;

    @OutputColAnnotation(colCoord = 3)
    private int orderStatus;

    @OutputColAnnotation(colCoord = 4)
    private String name;

    @OutputColAnnotation(colCoord = 5)
    private String tel;

    @OutputColAnnotation(colCoord = 6)
    private String cardNum;

    @OutputColAnnotation(colCoord = 7)
    private String idCardNum;

    @OutputColAnnotation(colCoord = 8)
    private BigDecimal cardAmount;

    @OutputColAnnotation(colCoord = 9)
    private float saleRatio;

    @OutputColAnnotation(colCoord = 10)
    private String cardTypeName;

    @OutputColAnnotation(colCoord = 11)
    private Timestamp processTime;

    @OutputColAnnotation(colCoord = 12)
    private String thirdMsg;

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public float getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(float saleRatio) {
        this.saleRatio = saleRatio;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public Timestamp getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Timestamp processTime) {
        this.processTime = processTime;
    }

    public String getThirdMsg() {
        return thirdMsg;
    }

    public void setThirdMsg(String thirdMsg) {
        this.thirdMsg = thirdMsg;
    }

    @Override
    public String toString() {
        return "QueryOrderListAsListOutput{" +
                ", orderTime=" + orderTime +
                ", orderNum='" + orderNum + '\'' +
                ", actualAmount=" + actualAmount +
                ", orderStatus=" + orderStatus +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", cardAmount=" + cardAmount +
                ", saleRatio=" + saleRatio +
                ", cardTypeName='" + cardTypeName + '\'' +
                ", processTime=" + processTime +
                ", thirdMsg='" + thirdMsg + '\'' +
                '}';
    }
}
