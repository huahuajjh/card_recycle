package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_bank_account")
public class BankAccount extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "card_number")
    private String cardNum;

    @Column(name = "name")
    private String name;

    @Column(name = "tb_user_id")
    private Integer userId = 0;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "tb_bank_id")
    private Integer bankId = 0;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", bankName='" + bankName + '\'' +
                ", bankId=" + bankId +
                '}';
    }
}
