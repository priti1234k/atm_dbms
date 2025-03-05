package com.bank.atm.exception;

import lombok.Data;

@Data
public class BankNotFoundException extends RuntimeException{
    private String reason;

    public BankNotFoundException(String message) {
        super(message);
        this.reason = message;
    }
}
