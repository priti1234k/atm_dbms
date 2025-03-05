package com.bank.atm.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BankDeleteFailedException extends RuntimeException{
    private String reason;

    private HttpStatus status;

    public BankDeleteFailedException(String message, HttpStatus status) {
        super(message);
        this.reason = message;
        this.status = status;
    }
}
