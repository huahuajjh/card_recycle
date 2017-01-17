package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_rechargeable_card_type_item")
public class RechargeableCardTypeItem extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "tb_rechargeable_card_type_id")
    private Integer cardTypeId;

    @Column(name = "support_amount")
    private BigDecimal supportAmount;

    @Column(name = "sale_ratio")
    private float saleRatio;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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

    public float getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(float saleRatio) {
        this.saleRatio = saleRatio;
    }

    @Override
    public String toString() {
        return "RechargeableCardTypeItem{" +
                "id=" + id +
                ", cardTypeId=" + cardTypeId +
                ", supportAmount=" + supportAmount +
                ", saleRatio=" + saleRatio +
                '}';
    }
}
