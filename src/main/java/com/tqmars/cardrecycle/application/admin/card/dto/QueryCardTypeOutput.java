package com.tqmars.cardrecycle.application.admin.card.dto;

/**
 * Created by jjh on 1/17/17.
 */
public class QueryCardTypeOutput {
    private Integer id;
    private String name;
    private String cardCode;
    private float saleRatio;
    private String description;

//    private List<QueryCardTypeItemOutput> items;


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

    public Integer getId() {
        return id;
    }

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

//    public List<QueryCardTypeItemOutput> getItems() {
//        return items;
//    }

//    public void setItems(List<QueryCardTypeItemOutput> items) {
//        this.items = items;
//    }


    @Override
    public String toString() {
        return "QueryCardTypeOutput{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", saleRatio=" + saleRatio +
                ", description='" + description + '\'' +
                '}';
    }
}
