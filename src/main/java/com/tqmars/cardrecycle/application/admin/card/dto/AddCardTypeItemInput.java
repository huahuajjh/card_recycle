package com.tqmars.cardrecycle.application.admin.card.dto;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/17/17.
 */
public class AddCardTypeItemInput {
    private Integer cardTypeId;
    private BigDecimal supportAmount;

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public BigDecimal getSupportAmount() {
        return supportAmount;
    }

    public void setSupportAmount(BigDecimal supportAmount) {
        this.supportAmount = supportAmount;
    }
}
