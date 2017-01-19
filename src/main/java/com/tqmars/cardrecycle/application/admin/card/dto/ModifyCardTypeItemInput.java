package com.tqmars.cardrecycle.application.admin.card.dto;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/17/17.
 */
public class ModifyCardTypeItemInput {
    private Integer id;
    private Integer cardTypeId;
    private BigDecimal supportAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
