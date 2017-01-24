package com.tqmars.cardrecycle.application.card.dto;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/21/17.
 */
public class QueryCardItemOutput {
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
