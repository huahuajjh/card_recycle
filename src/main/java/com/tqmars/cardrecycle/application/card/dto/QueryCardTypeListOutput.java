package com.tqmars.cardrecycle.application.card.dto;

/**
 * Created by jjh on 1/21/17.
 */
public class QueryCardTypeListOutput {
    private Integer id;
    private String name;
    private String cardCode;
    private float saleRatio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
