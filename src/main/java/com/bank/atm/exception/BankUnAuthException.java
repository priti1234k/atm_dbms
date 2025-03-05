package com.bank.atm.exception;

import lombok.Data;

@Data
public class BankUnAuthException extends RuntimeException {
    private String reason;

    public BankUnAuthException(String message) {
        super(message);
        this.reason = message;
    }
}
