package com.bank.atm.exception;

import lombok.Data;

@Data
public class CardNotFoundException extends RuntimeException {
    private String reason;

    public CardNotFoundException(String message) {
        super(message);
        this.reason = message;
    }
}
