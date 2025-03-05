package com.bank.atm.exception;

import lombok.Data;

@Data
public class TransactionsCreationFailedException extends RuntimeException{
    private String reason;

    public TransactionsCreationFailedException(String message) {
        super(message);
        this.reason = message;
    }
}
