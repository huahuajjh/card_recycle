package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

/**
 * Created by jjh on 2/8/17.
 */
@Table(name = "v_card_type_and_items")
public class CardAndItems extends EntityOfIntPrimaryKey {
    @Column(name = "name")
    private String name;

    @Column(name = "sale_ratio")
    private float saleRatio;

    @Column(name = "card_shortcut")
    private String cardCode;

    @Column(name = "amounts")
    private String amounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(float saleRatio) {
        this.saleRatio = saleRatio;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }
}
