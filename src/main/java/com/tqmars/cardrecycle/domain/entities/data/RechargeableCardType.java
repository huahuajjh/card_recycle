package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.util.List;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_rechargeable_card_type")
public class RechargeableCardType extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "name")
    private String name;

    @Column(name = "card_shortcut")
    private String cardCode;

    @Column(name = "sale_ratio")
    private float saleRatio;

    @Column(name = "description")
    private String description;

    private List<RechargeableCardTypeItem> items;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(float saleRatio) {
        this.saleRatio = saleRatio;
    }

    public List<RechargeableCardTypeItem> getItems() {
        return items;
    }

    public void setItems(List<RechargeableCardTypeItem> items) {
        this.items = items;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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

    @Override
    public String toString() {
        return "RechargeableCardType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", saleRatio=" + saleRatio +
                ", description=" + description +
                '}';
    }
}
