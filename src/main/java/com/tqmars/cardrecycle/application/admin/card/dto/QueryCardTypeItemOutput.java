package com.tqmars.cardrecycle.application.admin.card.dto;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/17/17.
 */
public class QueryCardTypeItemOutput {
    private Integer id;
    private BigDecimal supportAmount;
    private Integer cardTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSupportAmount() {
        return supportAmount;
    }

    public void setSupportAmount(BigDecimal supportAmount) {
        this.supportAmount = supportAmount;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }
}
