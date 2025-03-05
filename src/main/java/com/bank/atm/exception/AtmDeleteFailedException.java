package com.bank.atm.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AtmDeleteFailedException extends RuntimeException{
    private String reason;

    private HttpStatus status;

    public AtmDeleteFailedException(String message, HttpStatus status) {
        super(message);
        this.reason = message;
        this.status = status;
    }
}
