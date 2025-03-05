package com.bank.atm.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomerCreationFailedException extends RuntimeException{
    private String reason;

    public CustomerCreationFailedException(String message) {
        super(message);
        this.reason = message;
    }
}
