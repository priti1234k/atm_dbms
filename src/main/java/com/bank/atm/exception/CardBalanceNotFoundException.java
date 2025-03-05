package com.bank.atm.exception;

import lombok.Data;

@Data
public class CardBalanceNotFoundException extends RuntimeException{
    private String reason;

    public CardBalanceNotFoundException(String message) {
        super(message);
        this.reason = message;
    }
}
