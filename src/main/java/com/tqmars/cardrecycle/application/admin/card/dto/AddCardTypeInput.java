package com.tqmars.cardrecycle.application.admin.card.dto;

/**
 * Created by jjh on 1/16/17.
 */
public class AddCardTypeInput {
    private String name;
    private String cardCode;
    private float saleRatio;
    private String description;
//    private List<AddCardTypeItemInput> items;


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


    @Override
    public String toString() {
        return "AddCardTypeInput{" +
                "name='" + name + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", saleRatio=" + saleRatio +
                '}';
    }
}
