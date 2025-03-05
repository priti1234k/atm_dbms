package com.bank.atm.models;

public enum TransactionType {

    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
