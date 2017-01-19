package com.tqmars.cardrecycle.application.admin.card.dto;

/**
 * Created by jjh on 1/16/17.
 */
public class AddCardTypeInput {
    private String name;
    private String cardCode;
    private float saleRatio;
//    private List<AddCardTypeItemInput> items;


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

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

//    public List<AddCardTypeItemInput> getItems() {
//        return items;
//    }

//    public void setItems(List<AddCardTypeItemInput> items) {
//        this.items = items;
//    }
}
