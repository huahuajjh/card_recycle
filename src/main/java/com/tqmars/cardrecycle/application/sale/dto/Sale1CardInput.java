package com.tqmars.cardrecycle.application.sale.dto;

/**
 * Created by jjh on 1/21/17.
 */
public class Sale1CardInput {
    private String cardNum;
    private String cardPwd;
    private String cardCode;
    private Integer userId;
    private Integer cardTypeId;
    private Integer cardItemId;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public Integer getCardItemId() {
        return cardItemId;
    }

    public void setCardItemId(Integer cardItemId) {
        this.cardItemId = cardItemId;
    }

    @Override
    public String toString() {
        return "Sale1CardInput{" +
                "cardNum='" + cardNum + '\'' +
                ", cardPwd='" + cardPwd + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", userId=" + userId +
                ", cardTypeId=" + cardTypeId +
                ", cardItemId=" + cardItemId +
                '}';
    }
}
