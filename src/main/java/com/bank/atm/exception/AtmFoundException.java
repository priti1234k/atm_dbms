package com.bank.atm.exception;

import lombok.Data;

@Data
public class AtmFoundException extends RuntimeException{
    private String reason;

    public AtmFoundException(String message) {
        super(message);
        this.reason = message;
    }
}
