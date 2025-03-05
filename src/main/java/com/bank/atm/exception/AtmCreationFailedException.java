package com.bank.atm.exception;

import lombok.Data;

@Data
public class AtmCreationFailedException extends RuntimeException{
    private String reason;

    public AtmCreationFailedException(String message) {
        super(message);
        this.reason = message;
    }
}
