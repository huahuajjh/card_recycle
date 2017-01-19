package com.tqmars.cardrecycle.domain.entities.data;

import com.tqmars.cardrecycle.domain.entities.EntityOfIntPrimaryKey;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by jjh on 1/14/17.
 */
@Table(name = "tb_wallet")
public class Wallet extends EntityOfIntPrimaryKey {
    @Column(name = "id")
    private Integer id = 0;

    @Column(name = "tb_user_id")
    private Integer userId = 0;

    @Column(name = "balance")
    private BigDecimal balance;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }

    public void withdraw(BigDecimal amount) throws Exception {
        if(balance.compareTo(amount) < 0){
            throw  new Exception("余额不足");
        }else {
            balance.subtract(amount);
        }
    }
}
