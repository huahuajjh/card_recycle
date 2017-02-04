package com.tqmars.cardrecycle.application.bank.dto;

/**
 * Created by jjh on 2/4/17.
 */
public class QueryAllBankOutput {
    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "QueryAllBankOutput{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
