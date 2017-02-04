package com.tqmars.cardrecycle.application.wallet.dto;

import java.math.BigDecimal;

/**
 * Created by jjh on 2/4/17.
 */
public class GetBalanceOutput {
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
