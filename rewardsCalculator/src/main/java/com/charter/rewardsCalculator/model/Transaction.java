package com.charter.rewardsCalculator.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Transaction {

    private String CustomerId;
    private int transactionValue;
    private LocalDate date;

    public Transaction(String customerId, int transactionValue, LocalDate date) {
        CustomerId = customerId;
        this.transactionValue = transactionValue;
        this.date = date;
    }
}
