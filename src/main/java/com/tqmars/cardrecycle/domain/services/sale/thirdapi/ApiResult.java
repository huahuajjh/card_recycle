package com.tqmars.cardrecycle.domain.services.sale.thirdapi;

/**
 * Created by jjh on 1/22/17.
 */
public class ApiResult {
    private String resultCode;
    private String orderNo;
    private String merchOrderNo;
    private String message;
    private String sign;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "resultCode='" + resultCode + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", merchOrderNo='" + merchOrderNo + '\'' +
                ", message='" + message + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public static class ResultCode{
        public static String NOT_EXISTS_CARD_TYPE = "-1";
        public static String NOT_EXISTS_CARD_ITEM_AMOUNT = "-2";
        public static String NOT_EXISTS_BUSINESS = "-3";
    }

}
