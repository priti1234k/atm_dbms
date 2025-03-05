package com.bank.atm.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AtmBalanceNotFoundException extends RuntimeException{
    private String reason;
    public AtmBalanceNotFoundException(String message) {
        super(message);
        this.reason = message;
    }
}
