package com.bank.atm.exception;

import lombok.Data;

@Data
public class CardCreationFailedException extends RuntimeException{
    private String reason;

    public CardCreationFailedException(String message) {
        super(message);
        this.reason = message;
    }
}
