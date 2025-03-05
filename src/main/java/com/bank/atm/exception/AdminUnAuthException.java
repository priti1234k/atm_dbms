package com.bank.atm.exception;

import lombok.Data;

@Data
public class AdminUnAuthException extends RuntimeException {
    private String reason;

    public AdminUnAuthException(String message) {
        super(message);
        this.reason = message;
    }
}
