package com.tqmars.cardrecycle.application.callback.dto;

/**
 * Created by jjh on 2/6/17.
 */
public class OrderCallbackInput {
    private String resultCode;
    private String orderNo;
    private String merchOrderNo;
    private String message;
    private String par;
    private String realAmount;
    private String sign;

    public OrderCallbackInput(String resultCode, String orderNo, String merchOrderNo, String message, String par, String realAmount, String sign) {
        this.resultCode = resultCode;
        this.orderNo = orderNo;
        this.merchOrderNo = merchOrderNo;
        this.message = message;
        this.par = par;
        this.realAmount = realAmount;
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMerchOrderNo() {
        return merchOrderNo;
    }

    public void setMerchOrderNo(String merchOrderNo) {
        this.merchOrderNo = merchOrderNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "OrderCallbackInput{" +
                "resultCode='" + resultCode + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", merchOrderNo='" + merchOrderNo + '\'' +
                ", message='" + message + '\'' +
                ", par='" + par + '\'' +
                ", realAmount='" + realAmount + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
