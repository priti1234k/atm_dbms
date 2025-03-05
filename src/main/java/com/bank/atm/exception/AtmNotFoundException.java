package com.bank.atm.exception;

import lombok.Data;

@Data
public class AtmNotFoundException extends RuntimeException{
    private String reason;

    public AtmNotFoundException(String message) {
        super(message);
        this.reason = message;
    }
}
