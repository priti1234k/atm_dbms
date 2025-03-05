package com.bank.atm.exception;

import lombok.Data;

@Data
public class BankCreationFailedException extends RuntimeException{
    private String reason;

    public BankCreationFailedException(String message) {
        super(message);
        this.reason = message;
    }
}
