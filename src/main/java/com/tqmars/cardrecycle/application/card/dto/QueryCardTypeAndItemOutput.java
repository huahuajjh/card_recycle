package com.tqmars.cardrecycle.application.card.dto;

/**
 * Created by jjh on 2/8/17.
 */
public class QueryCardTypeAndItemOutput {
    private float saleRatio;
    private String name;
    private String amounts;
    private String cardCode;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public float getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(float saleRatio) {
        this.saleRatio = saleRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    @Override
    public String toString() {
        return "QueryCardTypeAndItemOutput{" +
                "saleRatio=" + saleRatio +
                ", name='" + name + '\'' +
                ", amounts='" + amounts + '\'' +
                ", cardCode='" + cardCode + '\'' +
                '}';
    }
}
