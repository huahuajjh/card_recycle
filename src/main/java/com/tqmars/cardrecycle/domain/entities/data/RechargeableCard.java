package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_rechargeable_card")
public class RechargeableCard extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "card_number")
    private String cardNum;

    @Column(name = "card_pwd")
    private String cardPwd;

    @Column(name = "tb_user_id")
    private Integer userId;

    @Column(name = "tb_rechargeable_card_type_id")
    private Integer cardTypeId;

    @Column(name = "tb_rechargeable_card_type_item_id")
    private Integer cardItemId;

    @Column(name = "sale_ratio")
    private float saleRatio;

    @Column(name = "support_amount")
    private BigDecimal supportAmount;

    public float getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(float saleRatio) {
        this.saleRatio = saleRatio;
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

    public Integer getCardItemId() {
        return cardItemId;
    }

    public void setCardItemId(Integer cardItemId) {
        this.cardItemId = cardItemId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RechargeableCard{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                ", cardPwd='" + cardPwd + '\'' +
                ", userId=" + userId +
                '}';
    }
}
