package com.bank.atm.exception;

import lombok.Data;

@Data
public class LoginException extends RuntimeException {
    private String reason;

    public LoginException(String message) {
        super(message);
        this.reason = message;
    }
}
